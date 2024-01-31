package com.almatar.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SortButton(modifier: Modifier = Modifier,onSort: () -> Unit) {
    IconButton(
        onClick = onSort,
        modifier = modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Outlined.Sort, contentDescription = "Sort")
    }
}

@Preview
@Composable
private fun SortButtonPreview(){
    SortButton {

    }
}