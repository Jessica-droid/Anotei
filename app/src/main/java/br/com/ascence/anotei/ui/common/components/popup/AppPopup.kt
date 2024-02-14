package br.com.ascence.anotei.ui.common.components.popup

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@Composable
fun AppPopup(
    isPopupVisible: Boolean,
    onDismissRequest: () -> Unit,
    offset: DpOffset = DpOffset(AnoteiAppTheme.spaces.none, AnoteiAppTheme.spaces.none),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable (MutableTransitionState<Boolean>, TransformOrigin) -> Unit,
) {

    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = isPopupVisible

    if (expandedStates.currentState || expandedStates.targetState) {

        val density = LocalDensity.current

        val positionProvider = PopupDialogPositionProvider(
            contentOffset = offset,
            density = density
        )

        Popup(
            popupPositionProvider = positionProvider,
            properties = properties,
            onDismissRequest = onDismissRequest,
            content = {
                content(
                    expandedStates,
                    TransformOrigin(
                        pivotFractionX = 1f,
                        pivotFractionY = 0f
                    )
                )
            }
        )
    }
}