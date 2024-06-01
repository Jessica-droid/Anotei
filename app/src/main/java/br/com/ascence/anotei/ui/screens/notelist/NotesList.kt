package br.com.ascence.anotei.ui.screens.notelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.ascence.anotei.data.mock.notesListMock
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.ui.screens.notelist.components.notecard.NoteCard
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NotesListScreen(
    notesList: List<Note>,
    selectedNoteId: Int,
    onNoteClick: (Note) -> Unit,
    shouldResetScroll: Boolean,
    modifier: Modifier = Modifier,
) {
    ListContent(
        notes = notesList,
        selectedId = selectedNoteId,
        onNoteClick = { note ->
            onNoteClick(note)
        },
        shouldResetScroll = shouldResetScroll,
        modifier = modifier,
    )
}

@Composable
private fun ListContent(
    notes: List<Note>,
    selectedId: Int,
    onNoteClick: (Note) -> Unit,
    shouldResetScroll: Boolean,
    modifier: Modifier = Modifier,
) {
    if (notes.isNotEmpty()) {
        Notes(
            notes = notes,
            selectedId = selectedId,
            onNoteClick = onNoteClick,
            shouldResetScroll = shouldResetScroll,
            modifier = modifier,
        )
    } else {
        NotesEmptyState(
            modifier = modifier
                .fillMaxSize()
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}

@Composable
private fun Notes(
    notes: List<Note>,
    selectedId: Int,
    onNoteClick: (Note) -> Unit,
    shouldResetScroll: Boolean,
    modifier: Modifier = Modifier,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(shouldResetScroll) {
        if (shouldResetScroll) {
            listState.scrollToItem(index = 0)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.background(color = AnoteiAppTheme.colors.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(AnoteiAppTheme.spaces.xSmall),
    ) {
        items(
            notes,
            key = { note -> note.id }
        ) { note ->
            NoteCard(
                note = note,
                onCardClick = onNoteClick,
                isCardSelected = note.id == selectedId,
                modifier = Modifier.padding(horizontal = AnoteiAppTheme.spaces.medium)
            )
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NotesListPreview() {
    AnoteiTheme {
        ListContent(
            notes = notesListMock,
            selectedId = -1,
            onNoteClick = {},
            shouldResetScroll = false
        )
    }
}

@ColorSchemePreviews
@Composable
private fun NotesListEmptyPreview() {
    AnoteiTheme {
        ListContent(
            notes = emptyList(),
            selectedId = -1,
            onNoteClick = {},
            shouldResetScroll = false
        )
    }
}