package com.dennnisver4.habithub.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Material 3 Expressive Shapes - Android 15 Baklava
 * Esquinas extra redondeadas para un look más expresivo y moderno
 */
val ExpressiveShapes =
    Shapes(
        // Extra Small: para chips pequeños, badges
        extraSmall = RoundedCornerShape(8.dp),
        // Small: para botones pequeños, text fields
        small = RoundedCornerShape(12.dp),
        // Medium: para cards, dialogs - EXTRA REDONDEADO
        medium = RoundedCornerShape(20.dp),
        // Large: para bottom sheets, floating action buttons - MUY REDONDEADO
        large = RoundedCornerShape(28.dp),
        // Extra Large: para contenedores grandes - SUPER REDONDEADO
        extraLarge = RoundedCornerShape(36.dp),
    )
