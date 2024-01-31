package com.almatar.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.almatar.feature.home.navigation.HOME_ROUTE
import com.almatar.feature.home.navigation.homeScreen
import com.almatar.feature.upsert_product.navigation.navigateToUpsert
import com.almatar.feature.upsert_product.navigation.upsertScreen
import com.almatar.shoppingapp.ui.AlmatarAppState


/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AlmatarNavHost(
    appState: AlmatarAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onEditClick = {
            navController.navigateToUpsert(
                null, it
            )
        }, onCreateProductClicked = {
            navController.navigateToUpsert(null)
        }
        )
        upsertScreen(navController, onSaveClicked = navController::popBackStack)
    }
}
