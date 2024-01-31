
package com.almatar.core.domain


import com.almatar.core.data.model.Product
import com.almatar.core.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case which returns all products.
 */
class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
     operator fun invoke(searchQuery: String, isAsc : Boolean, showBoughtProducts:Boolean): Flow<List<Product>> =
        productRepository.getProducts(searchQuery, isAsc, showBoughtProducts)
}
