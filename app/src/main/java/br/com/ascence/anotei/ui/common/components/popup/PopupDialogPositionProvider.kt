package br.com.ascence.anotei.ui.common.components.popup

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

data class PopupDialogPositionProvider(
    val contentOffset: DpOffset,
    val density: Density,
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetX = with(density) { contentOffset.x.roundToPx() }
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetY = with(density) { contentOffset.y.roundToPx() }

        val toBottom = anchorBounds.bottom + contentOffsetY - popupContentSize.height
        val startPlacementOffset = anchorBounds.left + contentOffsetX - popupContentSize.width

        return IntOffset(startPlacementOffset, toBottom)
    }
}