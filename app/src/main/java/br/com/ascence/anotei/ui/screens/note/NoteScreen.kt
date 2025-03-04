package br.com.ascence.anotei.ui.screens.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementations.NotesRepositoryImp
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.navigation.NOTE_TYPE_EXTRA
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.navigation.extensions.popBackStackWithResult
import br.com.ascence.anotei.navigation.extensions.toNoteOptionsMode
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsWidget
import br.com.ascence.anotei.ui.common.components.popup.AppPopup
import br.com.ascence.anotei.ui.common.components.popup.contents.NoteCategorySelection
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
    navController: NavController,
    noteType: NoteType,
    noteId: String?,
) {

    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        NoteScreenViewModel(notesRepository = repository)
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
            DateHelper().formatDateToString(state.textNote.creationDate)
        }

    LaunchedEffect(noteType) {
        when (noteType) {
            NoteType.NEW_NOTE -> viewModel.fetchNoteContent(noteType)
            NoteType.DISPLAY_NOTE -> viewModel.fetchNoteContent(
                noteType,
                noteId
            )
        }
    }

    BackHandler(enabled = state.contentAlert == null) {
        viewModel.handleOnBackPressed(
            noteType = noteType,
        ) { result -> navController.popBackStackWithResult(NOTE_TYPE_EXTRA, result) }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .wrapContentHeight()
            .imePadding(),
        topBar = {
            NoteAppBar {
                viewModel.handleOnBackPressed(
                    noteType = noteType,
                ) { result -> navController.popBackStackWithResult(NOTE_TYPE_EXTRA, result) }
            }
        },
        bottomBar = {
            if (state.showEditMode) {
                NoteOptionsWidget(
                    mode = noteType.toNoteOptionsMode(),
                    selectedNotes = listOfNotNull(state.textNote),
                    onOptionClick = { noteOption -> viewModel.handleNoteOptionClick(noteOption) },
                    onFABClick = {
                        viewModel.handleNote(
                            noteType = noteType,
                            note = state.textNote,
                            onSaveNote = { onSaveResult ->
                                viewModel.dismissOrDisplayMode(
                                    noteType = noteType,
                                    onCloseScreen = {
                                        navController.popBackStackWithResult(
                                            NOTE_TYPE_EXTRA,
                                            onSaveResult
                                        )
                                    })
                            }
                        )
                    },
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AnoteiAppTheme.colors.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                state.contentAlert?.let {
                    SimpleDialog(
                        title = it.title,
                        message = it.message,
                        confirmLabel = it.confirmButtonLabel,
                        dismissLabel = it.dismissButtonLabel,
                        onConfirm = {
                            viewModel.handleAlertOption(
                                alertType = it,
                                noteType = noteType,
                                noteId = noteId,
                                onCloseScreen = { result ->
                                    navController.popBackStackWithResult(
                                        NOTE_TYPE_EXTRA,
                                        result
                                    )
                                }
                            )
                        },
                        onDismiss = { viewModel.hideAlertDialog() }
                    )
                }

                NoteHeader(
                    noteCategoryColor = state.textNote.category.getColor(),
                    titleInitialValue = state.textNote.title,
                    creationDateValue = noteCreationDate,
                    onTitleChanged = { newTitle ->
                        viewModel.onTitleUpdate(newTitle)
                    },
                    isNewNote = isNewNote,
                    titleMaxLength = TITLE_MAX_LENGTH.toString(),
                    isEditModeActivated = state.showEditMode,
                    focusRequester = focusRequester,
                    onTitleDone = { },
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
                                onCategorySelected = { category ->
                                    viewModel.updateNoteCategory(
                                        category
                                    )
                                }
                            )
                        }
                    )

                    BasicTextField(
                        value = state.textNote.description,
                        readOnly = state.showEditMode.not(),
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
            if (isNewNote.not() && state.showEditMode.not()) {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        viewModel.enableEditMode()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(AnoteiAppTheme.spaces.medium)
                )
                {
                    Text(text = "Editar")
                }
            }
        }
    }
}

private fun isNewNote(noteType: NoteType) = noteType == NoteType.NEW_NOTE

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
//        NoteScreenContent(
//            noteType = NoteType.NEW_NOTE,
//            noteId = null,
//            // onBackPressed = {}
//        )
    }
}