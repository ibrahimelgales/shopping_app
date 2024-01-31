package com.almatar.core.design_system.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingButton(icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(imageVector = icon, contentDescription = "Add")
    }
}

@Preview
@Composable
private fun FloatingButtonPreview() {
    FloatingButton(Icons.Default.Add) { }
}