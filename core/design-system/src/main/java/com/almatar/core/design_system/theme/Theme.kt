package com.almatar.core.design_system.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.Black,
)


val LightAndroidBackgroundTheme = BackgroundTheme(color = Color.White)


val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)


@Composable
fun AlmatarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme

    // Background theme
    val backgroundTheme = if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
    val tintTheme = TintTheme(colorScheme.primary)
    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AlmatarTypography,
            content = content,
        )
    }
}
