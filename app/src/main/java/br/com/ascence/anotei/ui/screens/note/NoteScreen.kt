package br.com.ascence.anotei.ui.screens.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsBar
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screens.note.components.NoteAppBar
import br.com.ascence.anotei.ui.screens.note.components.NoteHeader
import br.com.ascence.anotei.ui.screens.note.dialogs.ContentAlertDialog
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NoteScreenContent(
    onBackPressed: (String) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    val viewModel = remember {
        NoteScreenViewModel()
    }

    val state = viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler(enabled = state.value.showContentAlert.not()) {
        viewModel.handleOnBackPressed(onBackPressed)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .wrapContentHeight()
            .imePadding(),
        topBar = {
            NoteAppBar {
                viewModel.handleOnBackPressed(onBackPressed)
            }
        },
        bottomBar = {
            NoteOptionsBar(
                options = noteOptionsPreview,
                onFABClick = {}, // TODO setup note save
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
            if (state.value.showContentAlert) {
                ContentAlertDialog(
                    onDismiss = { viewModel.hideContentAlertDialog() },
                    onConfirm = { onBackPressed(NOTE_RESULT_NOTHING) }
                )
            }
            NoteHeader(
                noteCategoryColor = state.value.noteCategory.getColor(),
                titleInitialValue = state.value.title,
                onTitleChanged = { newTitle ->
                    viewModel.onTitleUpdate(newTitle)
                },
                modifier = Modifier.padding(horizontal = AnoteiAppTheme.spaces.medium)
            )
            BasicTextField(
                value = state.value.description,
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

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
        NoteScreenContent(onBackPressed = {})
    }
}