package com.almatar.core.design_system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


@Immutable
data class TintTheme(
    val iconTint: Color = Color.Unspecified,
)

/**
 * A composition local for [TintTheme].
 */
val LocalTintTheme = staticCompositionLocalOf { TintTheme() }
