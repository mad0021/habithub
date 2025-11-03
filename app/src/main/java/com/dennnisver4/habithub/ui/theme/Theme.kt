package com.dennnisver4.habithub.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Tema oscuro OLED - Negro puro para ahorrar batería en pantallas OLED
private val OLEDColorScheme =
    darkColorScheme(
        // Colores principales
        primary = Cyan, // Cyan brillante
        onPrimary = PureBlack, // Texto negro sobre cyan
        primaryContainer = DarkCyan, // Contenedor cyan oscuro
        onPrimaryContainer = LightCyan, // Texto claro sobre contenedor
        // Colores secundarios
        secondary = Teal, // Teal
        onSecondary = PureBlack,
        secondaryContainer = DarkTeal,
        onSecondaryContainer = LightTeal,
        // Colores terciarios
        tertiary = Green, // Verde brillante
        onTertiary = PureBlack,
        tertiaryContainer = DarkGreen,
        onTertiaryContainer = LightGreen,
        // Fondo y superficie - NEGRO PURO para OLED
        background = PureBlack, // Fondo negro puro
        onBackground = Color.White,
        surface = PureBlack, // Superficie negra pura
        onSurface = Color.White,
        surfaceVariant = NearBlack, // Variante casi negra
        onSurfaceVariant = LightCyan,
        surfaceContainer = NearBlack, // Contenedores casi negros
        surfaceContainerHigh = DarkGray, // Contenedores elevados gris oscuro
        surfaceContainerHighest = Color(0xFF2A2A2A),
        // Otros colores
        error = Color(0xFFCF6679),
        onError = PureBlack,
        outline = Cyan.copy(alpha = 0.5f),
        outlineVariant = ElectricBlue.copy(alpha = 0.3f),
    )

private val DarkColorScheme =
    darkColorScheme(
        // Colores principales - Gradiente azul a cyan a verde
        primary = Cyan, // Cyan brillante como color principal
        onPrimary = DeepBlue, // Texto sobre primary
        primaryContainer = DarkCyan, // Contenedor primary oscuro
        onPrimaryContainer = LightCyan, // Texto sobre contenedor
        // Colores secundarios
        secondary = Teal, // Teal como secundario
        onSecondary = Color.White,
        secondaryContainer = DarkTeal,
        onSecondaryContainer = LightTeal,
        // Colores terciarios
        tertiary = Green, // Verde brillante
        onTertiary = Color.White,
        tertiaryContainer = DarkGreen,
        onTertiaryContainer = LightGreen,
        // Fondo y superficie
        background = DeepBlue, // Fondo azul muy oscuro
        onBackground = Color.White,
        surface = DarkBlue, // Superficie azul oscuro
        onSurface = Color.White,
        surfaceVariant = Color(0xFF263238),
        onSurfaceVariant = LightCyan,
        // Otros colores
        error = Color(0xFFCF6679),
        onError = Color.Black,
        outline = Cyan.copy(alpha = 0.5f),
        outlineVariant = ElectricBlue.copy(alpha = 0.3f),
    )

private val LightColorScheme =
    lightColorScheme(
        // Colores principales - Gradiente azul a cyan a verde
        primary = ElectricBlue, // Azul eléctrico como principal
        onPrimary = Color.White,
        primaryContainer = LightCyan, // Contenedor claro
        onPrimaryContainer = DarkBlue,
        // Colores secundarios
        secondary = Teal, // Teal
        onSecondary = Color.White,
        secondaryContainer = LightTeal,
        onSecondaryContainer = DarkTeal,
        // Colores terciarios
        tertiary = Green, // Verde
        onTertiary = Color.White,
        tertiaryContainer = LightGreen,
        onTertiaryContainer = DarkGreen,
        // Fondo y superficie
        background = SurfaceLight, // Fondo claro
        onBackground = DeepBlue,
        surface = Color.White, // Superficie blanca
        onSurface = DeepBlue,
        surfaceVariant = Color(0xFFECEFF1),
        onSurfaceVariant = DarkBlue,
        // Otros colores
        error = Color(0xFFB00020),
        onError = Color.White,
        outline = ElectricBlue.copy(alpha = 0.5f),
        outlineVariant = Cyan.copy(alpha = 0.3f),
    )

@Composable
fun HabitHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color deshabilitado por defecto para usar nuestro tema personalizado
    dynamicColor: Boolean = false,
    // Usar tema OLED cuando está en modo oscuro
    useOledTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            // Usar colores dinámicos en Android 12+ (API 31+) si está habilitado
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            // Usar tema OLED si está en modo oscuro y useOledTheme es true
            darkTheme && useOledTheme -> OLEDColorScheme
            // Fallback a colores personalizados
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Hacer la barra de estado transparente para aspecto moderno
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            // Hacer la barra de navegación transparente (Android 15+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.navigationBarColor = android.graphics.Color.TRANSPARENT
            }
            // Ajustar iconos según el tema
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = ExpressiveShapes,
        content = content,
    )
}
