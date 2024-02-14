package br.com.ascence.anotei.ui.common.components.popup.contents

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@Composable
fun NoteCategorySelection(
    expandedStates: MutableTransitionState<Boolean>,
    transformOrigin: TransformOrigin,
    onCategorySelected: (Category) -> Unit,
) {

    val transition = updateTransition(expandedStates, "Popup")

    // Scale animation.
    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Scale"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Alpha animation.
    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Alpha"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Helper function for applying animations to graphics layer.
    fun GraphicsLayerScope.graphicsLayerAnim() {
        scaleX = scale
        scaleY = scale
        this.alpha = alpha
        this.transformOrigin = transformOrigin
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = AnoteiAppTheme.colors.bottomBarColor,
        ),
        modifier = Modifier
            .graphicsLayer { graphicsLayerAnim() }
            .padding(AnoteiAppTheme.spaces.small)
    ) {
        Row(
            modifier = Modifier.padding(AnoteiAppTheme.spaces.medium)
        ) {
            Category.entries.map { category ->
                IconButton(
                    onClick = { onCategorySelected(category) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Brightness1,
                        contentDescription = "",
                        tint = category.getColor()
                    )
                }
            }
        }
    }
}