package br.com.ascence.anotei.ui.screens.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsBar

@Composable
fun DashNoteOptionsBar(
    showBottomBar: Boolean,
    options: List<NoteOption>,
    onFABClick: () -> Unit,
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
        NoteOptionsBar(
            onFABClick = onFABClick,
            options = options,
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
        )
    }
}