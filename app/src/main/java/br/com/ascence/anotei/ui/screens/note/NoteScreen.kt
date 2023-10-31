package br.com.ascence.anotei.ui.screens.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavController
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screencomponents.shared.noteoptions.NoteOptionsBar
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
private fun NoteScreenContent(
    onBackPressed: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val content: MutableState<String> = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(onBackPressed) },
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
            NoteHeader()
            BasicTextField(
                value = content.value,
                textStyle = TextStyle(
                    color = AnoteiAppTheme.colors.secondaryTextColor,
                    fontSize = AnoteiAppTheme.fontSizes.medium,
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { newContent -> content.value = newContent },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AnoteiAppTheme.spaces.medium)
                    .focusRequester(focusRequester)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Criar anotação", // TODO replace this type of string
                tint = AnoteiAppTheme.colors.colorScheme.primary,
                modifier = Modifier.clickable {
                    onBackPressed()
                }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = AnoteiAppTheme.colors.colorScheme.background
        )
    )
}

@Composable
private fun NoteHeader() {

    val title: MutableState<String> = remember { mutableStateOf("Sem título") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AnoteiAppTheme.spaces.medium)
    ) {
        Icon(
            imageVector = Icons.Default.Brightness1,
            tint = AnoteiAppTheme.colors.allChipColor,
            contentDescription = "Cor da categoria",
            modifier = Modifier
                .padding(end = AnoteiAppTheme.spaces.medium)
                .size(AnoteiAppTheme.spaces.xxxLarge)
        )
        Column {
            BasicTextField(
                value = title.value,
                singleLine = true,
                textStyle = TextStyle(
                    color = AnoteiAppTheme.colors.primaryTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = AnoteiAppTheme.fontSizes.xLarge,
                ),
                onValueChange = { newTitle -> title.value = newTitle },
            )
            Text(
                text = "30 de Outubro",
                fontSize = AnoteiAppTheme.fontSizes.small,
                color = AnoteiAppTheme.colors.accentColor
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