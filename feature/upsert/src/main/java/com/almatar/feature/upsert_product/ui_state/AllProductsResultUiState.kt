package com.almatar.feature.upsert_product.ui_state

import com.almatar.core.data.model.Product


sealed interface AllProductsResultUiState {
    data object Loading : AllProductsResultUiState

    /**
     * This state mean there's no any products found; so we should
     * inform user with hint like no result found!.
     */
    data object NoProductsFound : AllProductsResultUiState

    data object LoadFailed : AllProductsResultUiState

    data class Success(
        val products: List<Product> = emptyList(),
    ) : AllProductsResultUiState
}
