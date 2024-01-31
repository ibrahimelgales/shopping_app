package com.almatar.core.data.repository

import com.almatar.core.data.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Data layer interface for products.
 */
interface ProductRepository {

    /**
     * Get products
     */
    fun getProducts(
        searchQuery: String,
        isAsc: Boolean,
        showBoughtProducts: Boolean
    ): Flow<List<Product>>

    /**
     * Insert or replace the [product].
     */
    suspend fun insertOrReplaceProduct(product: Product)

    /**
     * Delete product by id.
     */
    suspend fun deleteProductById(productId: Long)
}
