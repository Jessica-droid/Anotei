package br.com.ascence.anotei.ui.screens.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavController
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.common.components.noteoptions.NoteOptionsBar
import br.com.ascence.anotei.ui.screens.note.components.NoteAppBar
import br.com.ascence.anotei.ui.screens.note.components.NoteHeader
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NoteScreen(navController: NavController) {

    NoteScreenContent {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenContent(
    onBackPressed: (String) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }
    val noteTitleMaxLength: Int = remember { 40 }
    val noteTitle: MutableState<String> = remember { mutableStateOf("Sem título") }
    val noteContent: MutableState<String> = remember { mutableStateOf("") }
    val noteCategoryColor: Color = AnoteiAppTheme.colors.allChipColor

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .wrapContentHeight()
            .imePadding(),
        topBar = { NoteAppBar(onBackPressed) },
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
            NoteHeader(
                noteCategoryColor = noteCategoryColor,
                titleInitialValue = noteTitle.value,
                onTitleChanged = { newTitle ->
                    if (newTitle.length <= noteTitleMaxLength) {
                        noteTitle.value = newTitle
                    }
                },
                modifier = Modifier.padding(horizontal = AnoteiAppTheme.spaces.medium)
            )
            BasicTextField(
                value = noteContent.value,
                textStyle = TextStyle(
                    color = AnoteiAppTheme.colors.secondaryTextColor,
                    fontSize = AnoteiAppTheme.fontSizes.medium,
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { newContent -> noteContent.value = newContent },
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