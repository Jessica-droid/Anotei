package br.com.ascence.anotei.ui.common.components.noteoptions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.utils.noteoptions.NoteOptionsHandler
import br.com.ascence.anotei.ui.common.components.noteoptions.presentation.NoteOptionsMode
import br.com.ascence.anotei.ui.common.components.noteoptions.preview.NoteOptionsPreviewParams
import br.com.ascence.anotei.ui.common.components.noteoptions.preview.NoteOptionsWidgetPreviewProvider
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
internal fun NoteOptionsWidget(
    mode: NoteOptionsMode,
    selectedNotes: List<Note>,
    isSelectionModeActivated: Boolean,
    onFABClick: () -> Unit,
    onOptionClick: (NoteOption) -> Unit,
) {

    BottomAppBar(
        containerColor = AnoteiAppTheme.colors.bottomBarColor,
        actions = {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                NoteOptionsHandler().getNoteOptions(mode, selectedNotes).map {
                    NoteOption(
                        option = it,
                        showCheckBadge = false,
                        onClick = { option -> onOptionClick(option) }
                    )
                }

                if (isSelectionModeActivated) {
                    SelectedNotesCounter(selectedNotesCount = selectedNotes.size)
                }
            }
        },
        floatingActionButton = {
            if (isSelectionModeActivated.not()) {
                WidgetMainAction(
                    optionType = mode,
                    onFABClick = onFABClick
                )
            }
        }
    )
}

@Composable
private fun SelectedNotesCounter(selectedNotesCount: Int) {
    Text(
        text = selectedNotesCount.toString(),
        color = AnoteiAppTheme.colors.menuColor,
        fontWeight = FontWeight.Bold,
        fontSize = AnoteiAppTheme.fontSizes.xLarge,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = AnoteiAppTheme.spaces.xLarge
            )
    )
}

@Composable
private fun WidgetMainAction(
    optionType: NoteOptionsMode,
    onFABClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        containerColor = AnoteiAppTheme.colors.bottomBarFabColor,
        onClick = onFABClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = optionType.fabIcon,
            contentDescription = optionType.fabContentDescription,
            tint = optionType.fabIconColor(),
        )
    }
}

@ColorSchemePreviews
@Composable
private fun NoteOptionsPreview(
    @PreviewParameter(NoteOptionsWidgetPreviewProvider::class) mode: NoteOptionsPreviewParams,
) {
    AnoteiTheme {
        NoteOptionsWidget(
            mode = mode.mode,
            selectedNotes = mode.selectedNotesCount,
            isSelectionModeActivated = mode.selectionModeActivated,
            onFABClick = {},
            onOptionClick = {}
        )
    }
}