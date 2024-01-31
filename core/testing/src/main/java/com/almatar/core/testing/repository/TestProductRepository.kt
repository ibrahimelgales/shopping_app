package com.almatar.core.testing.repository

import com.almatar.core.data.model.Product
import com.almatar.core.data.repository.ProductRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class TestProductRepository : ProductRepository {

    private val productsFlow: MutableSharedFlow<List<Product>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val currentProducts = mutableListOf<Product>()

     val productIdsSelected = productsFlow.mapLatest { it.map { it.id } }.drop(2)


    override fun getProducts(searchQuery: String, isAsc: Boolean): Flow<List<Product>> {
        return if (searchQuery.isBlank()) {
            productsFlow
        } else {
            productIdsSelected.mapLatest { it.toSet() }.distinctUntilChanged().flatMapLatest { set ->
                productsFlow.map { flow -> flow.filter { set.contains(it.id) } }
            }
        }
    }

    override suspend fun insertOrReplaceProduct(product: Product) {
        currentProducts.add(product)
        productsFlow.tryEmit(currentProducts.toList())
    }

    override suspend fun deleteProductById(productId: Long) {
        val removedProduct = currentProducts.find { it.id == productId }
        removedProduct?.let {
            currentProducts.remove(it)
            productsFlow.tryEmit(currentProducts.toList())
        }
    }
}
