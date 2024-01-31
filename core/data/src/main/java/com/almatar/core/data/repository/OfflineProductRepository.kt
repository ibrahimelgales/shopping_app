package com.almatar.core.data.repository


import com.almatar.core.common.network.AppDispatchers
import com.almatar.core.common.network.Dispatcher
import com.almatar.core.data.model.Product
import com.almatar.core.data.model.asEntity
import com.almatar.core.data.model.asExternalModel
import com.almatar.core.database.dao.ProductDao
import com.almatar.core.database.dao.ProductDaoFts
import com.almatar.core.database.model.asEntityFts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class OfflineProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val productDaoFts: ProductDaoFts,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ProductRepository {

    override fun getProducts(searchQuery: String, isAsc: Boolean): Flow<List<Product>> {

        val productFlow = if (searchQuery.isBlank()) {
            productDao.getAllProducts(isAsc)
        } else {
            val productIds = productDaoFts.searchAllProducts("*$searchQuery*")
            productIds.mapLatest { it.toSet() }.distinctUntilChanged().flatMapLatest {
                productDao.getProductEntities(it, isAsc)
            }
        }

        return productFlow.map { item -> item.map { it.asExternalModel() } }
    }

    override suspend fun insertOrReplaceProduct(product: Product) {
        withContext(ioDispatcher) {
            val productEntity = product.asEntity()
            productDao.insertOrReplaceProduct(productEntity)
            productDaoFts.insertOrReplaceProduct(productEntity.asEntityFts())
        }
    }

    override suspend fun deleteProductById(productId: Long) {
        withContext(ioDispatcher) {
            productDao.deleteProductById(productId)
            productDaoFts.deleteProductById(productId)
        }
    }
}
