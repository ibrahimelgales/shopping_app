package com.almatar.feature.upsert_product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.almatar.core.data.model.Product
import com.almatar.feature.upsert_product.UpsertRoute

const val UPSERT_ROUTE = "upsert_route"
private const val ARGUMENT_PRODUCT = "ARGUMENT_PRODUCT"

fun NavController.navigateToUpsert(navOptions: NavOptions?, product: Product? = null) {
    currentBackStackEntry?.savedStateHandle?.set(ARGUMENT_PRODUCT, product)
    navigate(UPSERT_ROUTE, navOptions)
}

fun NavGraphBuilder.upsertScreen(navController: NavController,onSaveClicked: () -> Unit,) {
    composable(route = UPSERT_ROUTE) {
        val product = navController.previousBackStackEntry?.savedStateHandle?.get<Product?>(ARGUMENT_PRODUCT)
        UpsertRoute(product, onSaveClicked = onSaveClicked)
    }
}
