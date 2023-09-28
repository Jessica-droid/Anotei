package br.com.ascence.anotei.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.data.mock.notesListMock
import br.com.ascence.anotei.extension.toStatusPresentation
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.ui.screencomponents.notecard.NoteCard
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NotesList(
    notes: List<Note>,
    onNoteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.background(color = AnoteiAppTheme.colors.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(AnoteiAppTheme.spaces.xSmall),
    ) {
        items(notes) { note ->
            NoteCard(
                title = note.title,
                creationDate = note.creationDate,
                noteContent = note.description,
                noteColor = AnoteiAppTheme.colors.allChipColor,
                noteStatus = note.toStatusPresentation(),
                onCardClick = onNoteClick,
                modifier = Modifier.padding(horizontal = AnoteiAppTheme.spaces.medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesListLightPreview() {
    AnoteiTheme(darkTheme = false) {
        NotesList(
            notes = notesListMock,
            onNoteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesListDarkPreview() {
    AnoteiTheme(darkTheme = true) {
        NotesList(
            notes = notesListMock,
            onNoteClick = {}
        )
    }
}