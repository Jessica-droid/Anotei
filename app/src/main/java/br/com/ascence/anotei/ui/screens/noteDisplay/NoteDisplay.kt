package br.com.ascence.anotei.ui.screens.noteDisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementations.NotesRepositoryImp
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.fakeTextNote
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.ui.screens.noteDisplay.components.NoteHeaderDisplay
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.utils.VERY_LONG_NOTE_CONTENT
import br.com.ascence.anotei.utils.date.DateHelper

@Composable
internal fun NoteDisplay(
    noteId: String?,
) {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember { NoteDisplayViewModel(repository) }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        noteId?.let {
            viewModel.getNoteToDisplay(noteId = it)
        } ?: println(">>>>>> Não foi possível recuperar nota, ID inválido.")
    }

    TextNoteDisplayContent(
        note = state.note as? Note.TextNote
    )
}

@Composable
private fun TextNoteDisplayContent(
    note: Note.TextNote?,
) {
    note?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AnoteiAppTheme.colors.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(AnoteiAppTheme.spaces.medium)
                    .verticalScroll(rememberScrollState())
            ) {
                NoteHeaderDisplay(
                    categoryColor = note.category.getColor(),
                    title = note.title,
                    creationDate = DateHelper().formatDateToString(note.creationDate),
                )
                Text(
                    text = note.description,
                    fontSize = AnoteiAppTheme.fontSizes.medium,
                    color = AnoteiAppTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = AnoteiAppTheme.spaces.medium)
                )
                BasicTextField(
                    value = "Field normal",
                    textStyle = TextStyle(
                        color = AnoteiAppTheme.colors.secondaryTextColor,
                        fontSize = AnoteiAppTheme.fontSizes.medium,
                    ),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    onValueChange = { newContent ->
                        //viewModel.onDescriptionUpdate(newContent)
                    },
                    cursorBrush = SolidColor(AnoteiAppTheme.colors.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(AnoteiAppTheme.spaces.medium)
                      //  .focusRequester(focusRequester)
                )
                BasicTextField(
                    value = "Field normal",
                    readOnly = true,
                    textStyle = TextStyle(
                        color = AnoteiAppTheme.colors.secondaryTextColor,
                        fontSize = AnoteiAppTheme.fontSizes.medium,
                    ),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    onValueChange = { newContent ->
                        //viewModel.onDescriptionUpdate(newContent)
                    },
                    cursorBrush = SolidColor(AnoteiAppTheme.colors.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(AnoteiAppTheme.spaces.medium)
                    //  .focusRequester(focusRequester)
                )

            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Selecione uma nota",
            color = AnoteiAppTheme.colors.primaryTextColor,
            fontSize = AnoteiAppTheme.fontSizes.xLarge
        )
    }
}

@ColorSchemePreviews
@Composable
private fun NoteDisplayPreview() {
    AnoteiTheme {
        TextNoteDisplayContent(
            fakeTextNote.copy(
                description = VERY_LONG_NOTE_CONTENT,
            ),
        )
    }
}