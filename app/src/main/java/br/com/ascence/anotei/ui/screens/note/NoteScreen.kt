package br.com.ascence.anotei.ui.screens.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementations.NotesRepositoryImp
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsBar
import br.com.ascence.anotei.utils.noteoptions.NoteOptionsHelper
import br.com.ascence.anotei.ui.common.components.popup.AppPopup
import br.com.ascence.anotei.ui.common.components.popup.contents.NoteCategorySelection
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screens.note.NoteScreenViewModel.Companion.TITLE_MAX_LENGTH
import br.com.ascence.anotei.ui.screens.note.components.NoteAppBar
import br.com.ascence.anotei.ui.screens.note.components.NoteHeader
import br.com.ascence.anotei.ui.screens.note.dialogs.SimpleDialog
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.utils.date.DateHelper
import java.util.Date

@Composable
fun NoteScreenContent(
    noteType: NoteType,
    note: Note.TextNote?,
    onBackPressed: (String) -> Unit,
) {

    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        NoteScreenViewModel(repository)
    }

    val focusRequester = remember { FocusRequester() }

    val state by viewModel.screenState.collectAsState()

    val isNewNote = remember {
        isNewNote(noteType)
    }

    val noteCreationDate =
        if (isNewNote) {
            DateHelper().formatDateToString(Date())
        } else {
            state.creationDate
        }

    val noteOptions = NoteOptionsHelper().getOptions(
        noteType = noteType,
        noteCategory = state.noteCategory,
        onCategoryClick = { viewModel.showCategoryPopup() },
        onScheduleClick = { },
        onProtectClick = { },
        onDeleteClick = { viewModel.showDeleteNoteAlert() }
    )

    LaunchedEffect(noteType) {
        when (noteType) {
            NoteType.NEW_NOTE -> {
                viewModel.fetchNoteContent(noteType)
                focusRequester.requestFocus()
            }

            NoteType.UPDATE_NOTE -> viewModel.fetchNoteContent(noteType, note)
        }
    }

    BackHandler(enabled = state.showContentAlert.not()) {
        viewModel.handleOnBackPressed(noteType, onBackPressed)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .wrapContentHeight()
            .imePadding(),
        topBar = {
            NoteAppBar {
                viewModel.handleOnBackPressed(noteType, onBackPressed)
            }
        },
        bottomBar = {
            NoteOptionsBar(
                options = noteOptions,
                onFABClick = { viewModel.handleNote(noteType, note, onBackPressed) },
                optionType = NoteOptionsPresentationType.EDIT_MODE
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AnoteiAppTheme.colors.colorScheme.background)
        ) {
            if (state.showContentAlert) {
                SimpleDialog(
                    title = "Descartar nota?",
                    message = "Deseja descartar o que anotou até o momento?",
                    confirmLabel = "Confirmar",
                    dismissLabel = "Cancelar",
                    onDismiss = { viewModel.hideAlertDialog() },
                    onConfirm = { onBackPressed(NOTE_RESULT_NOTHING) }
                )
            }

            if (state.showEmptyNoteAlert) {
                SimpleDialog(
                    title = "Um momento!",
                    message = "Anote alguma coisa antes de salvar.",
                    confirmLabel = "Entendi",
                    onDismiss = { viewModel.hideAlertDialog() },
                    onConfirm = { viewModel.hideAlertDialog() }
                )
            }

            if (state.showNoteDiscardAlert) {
                SimpleDialog(
                    title = "Descartar nota",
                    message = "Deseja realmente descartar esta nota?",
                    confirmLabel = "Confirmar",
                    dismissLabel = "Cancelar",
                    onDismiss = { viewModel.hideAlertDialog() },
                    onConfirm = { viewModel.hideAlertDialog() }
                )
            }

            NoteHeader(
                noteCategoryColor = state.noteCategory.getColor(),
                titleInitialValue = state.title,
                creationDateValue = noteCreationDate,
                onTitleChanged = { newTitle ->
                    viewModel.onTitleUpdate(newTitle)
                },
                isNewNote = isNewNote,
                titleMaxLength = TITLE_MAX_LENGTH.toString(),
                onTitleDone = { focusRequester.requestFocus() },
                modifier = Modifier.padding(horizontal = AnoteiAppTheme.spaces.medium)
            )

            Box(propagateMinConstraints = true) {

                AppPopup(
                    isPopupVisible = state.showCategoryPopup,
                    onDismissRequest = { viewModel.hideAlertDialog() },
                    content = { states, tOrigin ->
                        NoteCategorySelection(
                            expandedStates = states,
                            transformOrigin = tOrigin,
                            onCategorySelected = { category -> viewModel.updateNoteCategory(category) }
                        )
                    }
                )

                BasicTextField(
                    value = state.description,
                    textStyle = TextStyle(
                        color = AnoteiAppTheme.colors.secondaryTextColor,
                        fontSize = AnoteiAppTheme.fontSizes.medium,
                    ),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    onValueChange = { newContent ->
                        viewModel.onDescriptionUpdate(newContent)
                    },
                    cursorBrush = SolidColor(AnoteiAppTheme.colors.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(AnoteiAppTheme.spaces.medium)
                        .focusRequester(focusRequester)
                )
            }
        }
    }
}

private fun isNewNote(noteType: NoteType) = noteType == NoteType.NEW_NOTE

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
        NoteScreenContent(
            noteType = NoteType.NEW_NOTE,
            note = null,
            onBackPressed = {}
        )
    }
}