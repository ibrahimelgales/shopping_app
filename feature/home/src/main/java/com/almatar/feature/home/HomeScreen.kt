package com.almatar.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.almatar.core.data.model.Product
import com.almatar.core.design_system.component.AlmatarLoadingWheel
import com.almatar.core.design_system.component.FloatingButton
import com.almatar.core.design_system.component.SearchTextField
import com.almatar.feature.home.ui_state.AllProductsResultUiState
import com.almatar.feature.home.ui_state.AllProductsResultUiState.LoadFailed
import com.almatar.feature.home.ui_state.AllProductsResultUiState.Loading
import com.almatar.feature.home.ui_state.AllProductsResultUiState.NoProductsFound
import com.almatar.feature.home.ui_state.AllProductsResultUiState.Success


@Composable
internal fun HomeRoute(
    onEditClick: (Product) -> Unit,
    onCreateProductClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val allProductsState by viewModel.allProductsResultUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    HomeScreen(
        allProductsState,
        onCreateProductClicked,
        viewModel::changeSortingById,
        viewModel::onSearchQueryChanged,
        onEditClick,
        viewModel::deleteProduct,
        modifier,
        searchQuery
    )
}

@Composable
internal fun HomeScreen(
    allProductsState: AllProductsResultUiState,
    onCreateProductClicked: () -> Unit,
    changeSortingById: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onEditClick: (Product) -> Unit,
    deleteProduct: (Long) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: String
) {


    Scaffold(floatingActionButton = {
        CreateAndSortFAB(
            onCreateProductClicked,
            changeSortingById
        )
    }, topBar = {
        SearchTextField(
            onSearchTriggered = {},
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged
        )
    }, content = { padding ->
        Box(Modifier.padding(padding)) {
            when (allProductsState) {
                Loading -> LoadingState(modifier)
                is Success -> ShoppingList(
                    shoppingList = allProductsState.products,
                    onEditClick = onEditClick,
                    onDeleteClick = deleteProduct
                )

                is NoProductsFound -> NoProductsFoundScreen()

                is LoadFailed -> {
                    // TODO: show snack bar with error
                }
            }
        }

    })
}


@Composable
fun ShoppingList(
    shoppingList: List<Product>,
    onEditClick: (Product) -> Unit,
    onDeleteClick: (Long) -> Unit,
) {
    LazyColumn {
        items(shoppingList) { item ->
            ShoppingListItem(
                item = item, onEditClick = onEditClick, onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun NoProductsFoundScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("No items in the shopping list. Add items by clicking the button below.")
    }
}

@Composable
private fun ShoppingListItem(
    item: Product, onEditClick: (Product) -> Unit, onDeleteClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start
                ) {
                    Text(text = item.name, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity: ${item.quantity}")
                    Text(text = "Description: ${item.description}")

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(text = "Bought?", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))

                        val icon: ImageVector = if (item.isProductBought) {
                            Icons.Default.CheckCircle
                        } else {
                            Icons.Default.Close
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = if (item.isProductBought) "Bought" else "Not Bought",
                            modifier = Modifier.size(16.dp),
                            tint = if (item.isProductBought) Color.Green else Color.Red
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEditClick(item) }, modifier = Modifier.weight(1f)
                ) {
                    Text("Edit", color = Color.White)
                }

                Button(
                    onClick = { onDeleteClick(item.id) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    AlmatarLoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("home:loading"),
        contentDesc = stringResource(id = R.string.feature_home_loading),
    )
}


@Composable
fun CreateFAB(onCreateProductClicked: () -> Unit) {
    Column {
        FloatingButton(Icons.Default.AddCircleOutline) {
            onCreateProductClicked()
        }
    }
}

@Composable
fun SortFAB(onSortClicked: () -> Unit) {
    FloatingButton(Icons.Default.SwapVert) {
        onSortClicked()
    }
}


@Composable
fun CreateAndSortFAB(onCreateProductClicked: () -> Unit, onSortClicked: () -> Unit) {
    Column {
        CreateFAB(onCreateProductClicked)
        Spacer(modifier = Modifier.height(15.dp))
        SortFAB(onSortClicked)
    }
}
