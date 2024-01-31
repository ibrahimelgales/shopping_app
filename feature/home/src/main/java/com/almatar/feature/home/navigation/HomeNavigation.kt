package com.almatar.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.almatar.core.data.model.Product
import com.almatar.feature.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    onEditClick: (Product) -> Unit,
    onCreateProductClicked: () -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(onEditClick,onCreateProductClicked)
    }
}
