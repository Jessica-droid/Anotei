package br.com.ascence.anotei.ui.screens.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsWidget
import br.com.ascence.anotei.ui.common.components.noteoptions.presentation.NoteOptionsMode

@Composable
fun DashNoteOptionsBar(
    showBottomBar: Boolean,
    isSelectionModeActivated: Boolean,
    selectedNotes: List<Note>,
    onFABClick: () -> Unit,
    onOptionClick: (NoteOption) -> Unit
) {
    AnimatedVisibility(
        visible = showBottomBar,
        enter = expandVertically(
            animationSpec = tween(200),
            expandFrom = Alignment.Bottom
        ),
        exit = shrinkVertically(
            animationSpec = tween(200),
            shrinkTowards = Alignment.Bottom
        )
    ) {
        NoteOptionsWidget(
            mode = NoteOptionsMode.PREVIEW_MODE,
            selectedNotes = selectedNotes,
            isSelectionModeActivated = isSelectionModeActivated,
            onFABClick = onFABClick,
            onOptionClick = onOptionClick
        )
    }
}