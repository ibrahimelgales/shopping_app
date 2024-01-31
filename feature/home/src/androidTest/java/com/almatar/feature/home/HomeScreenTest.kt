package com.almatar.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.almatar.core.testing.data.productResourcesTestData
import com.almatar.feature.home.ui_state.AllProductsResultUiState
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for [HomeScreenTest] composable.
 */
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_showsLoadingSpinner() {
        composeTestRule.setContent {
            HomeScreen(
                allProductsState = AllProductsResultUiState.Loading,
                onCreateProductClicked = {},
                changeSortingById = {},
                onSearchQueryChanged = {},
                onEditClick = {},
                deleteProduct = {},
                searchQuery = ""
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                composeTestRule.activity.resources.getString(R.string.feature_home_loading),
            )
            .assertExists()
    }

    @Test
    fun home_whenHasProducts_showsProducts() {

        composeTestRule.setContent {
            HomeScreen(
                allProductsState = AllProductsResultUiState.Success(
                    productResourcesTestData.take(1)
                ),
                onCreateProductClicked = {

                },
                changeSortingById = {},
                onSearchQueryChanged = {},
                onEditClick = {},
                deleteProduct = {},
                searchQuery = ""
            )

        }

        composeTestRule
            .onNodeWithText(
                productResourcesTestData[0].description,
                substring = true,
            )
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    productResourcesTestData[0].description,
                    substring = true,
                ),
            )


    }
}
