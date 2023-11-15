package br.com.ascence.anotei.ui.screens.notelist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementation.NotesRepositoryImp
import br.com.ascence.anotei.data.mock.notesListMock
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.ui.screens.notelist.components.notecard.NoteCard
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NotesListScreen(
    onNoteClick: (Note, Boolean) -> Unit,
    onBackPressed: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        NotesListViewModel(repository)
    }

    val notesListState by viewModel.uiState.collectAsState()
    val selectedNote = remember { mutableIntStateOf(-1) }
    val haveSelectedNote = remember { mutableStateOf(false) }

    BackHandler(
        enabled = haveSelectedNote.value
    ) {
        selectedNote.intValue = -1
        haveSelectedNote.value = false
        onBackPressed(haveSelectedNote.value)
    }

    if (notesListState.notes.isNotEmpty()) {
        Notes(
            notes = notesListState.notes,
            selectedId = selectedNote.intValue,
            onNoteClick = { note ->
                selectedNote.intValue = note.id
                haveSelectedNote.value = selectedNote.intValue > -1
                onNoteClick(note, haveSelectedNote.value)
            },
            modifier = modifier,
        )
    } else {
        NotesEmptyState()
    }

}

@Composable
private fun Notes(
    notes: List<Note>,
    selectedId: Int,
    onNoteClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {

    val listState = rememberLazyListState()

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
private fun NotesListLightPreview() {
    AnoteiTheme {
        Notes(
            notes = notesListMock,
            selectedId = -1,
            onNoteClick = {}
        )
    }
}