package com.almatar.feature.upsert_product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.almatar.core.data.model.Product
import com.almatar.core.design_system.component.AlmatarButton


@Composable
internal fun UpsertRoute(
    product: Product?,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpsertViewModel = hiltViewModel(),
) {
    UpsertScreen(
        product, onSaveClicked = {
            onSaveClicked()
            viewModel.upsertProduct(it)
        }, modifier
    )
}

@Composable
internal fun UpsertScreen(
    product: Product?,
    onSaveClicked: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    val id by remember { mutableLongStateOf(product?.id ?: System.currentTimeMillis()) }
    var name by remember { mutableStateOf(product?.name.orEmpty()) }
    var quantity by remember { mutableIntStateOf(product?.quantity ?: 0) }
    var description by remember { mutableStateOf(product?.description.orEmpty()) }
    var isProductBought by remember { mutableStateOf(product?.isProductBought ?: false) }
    val isEdit by remember { mutableStateOf(product != null) }

    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            )

        TextField(
            value = quantity.toString(),
            onValueChange = { quantity = it.toIntOrNull() ?: 0 },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            )

        if (isEdit)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Product Bought")
                Switch(
                    checked = isProductBought,
                    onCheckedChange = { isProductBought = it },
                )
            }

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            AlmatarButton(modifier = Modifier.wrapContentWidth(),
                text = { Text(if (isEdit) "Update" else "Create") },
                onClick = {
                    onSaveClicked(
                        Product(
                            id, name, quantity, description, isProductBought
                        )
                    )
                })
        }
    }

}

