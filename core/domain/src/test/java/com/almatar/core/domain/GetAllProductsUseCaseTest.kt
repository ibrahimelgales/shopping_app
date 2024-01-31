package com.almatar.core.domain

import com.almatar.core.testing.data.productResourcesTestData
import com.almatar.core.testing.repository.TestProductRepository
import com.almatar.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetAllProductsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val productRepository = TestProductRepository()

    val useCase = GetAllProductsUseCase(
        productRepository
    )


    @Test
    fun whenQueryBlank_allProductsAreReturned() = runTest {
        // Obtain a stream of all products since query is blank.
        val allProducts = useCase("", true)

        // Send some test products.
        productResourcesTestData.forEach {
            productRepository.insertOrReplaceProduct(it)
        }


        // Check that the allProducts are returned when Query is Blank.
        assertEquals(
            productResourcesTestData,
            allProducts.first(),
        )
    }


}
