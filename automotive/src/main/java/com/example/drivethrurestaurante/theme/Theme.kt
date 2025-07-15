package com.example.drivethrurestaurante.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = RedPrimary,
    secondary = RedDark,
    tertiary = RedLight,

    background = Background,
    surface = SurfaceLight,
    surfaceVariant = SurfaceGray,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,

    outline = DividerColor,
    outlineVariant = IconGray,

    error = Error,
    onError = Color.White,

    // Colores para contenedores
    primaryContainer = RedLight,
    onPrimaryContainer = TextPrimary,
    secondaryContainer = SurfaceGray,
    onSecondaryContainer = TextSecondary,
)

// Opcional: Esquema de colores oscuro para futuro uso
private val DarkColors = darkColorScheme(
    primary = RedLight,
    secondary = RedPrimary,
    tertiary = RedDark,

    background = Color(0xFF1C1C1E),
    surface = Color(0xFF2C2C2E),
    surfaceVariant = Color(0xFF3A3A3C),

    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFB0B0B0),

    outline = Color(0xFF404040),
    outlineVariant = Color(0xFF606060),

    error = Color(0xFFFF6B6B),
    onError = Color.Black,
)

@Composable
fun RestauranteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}