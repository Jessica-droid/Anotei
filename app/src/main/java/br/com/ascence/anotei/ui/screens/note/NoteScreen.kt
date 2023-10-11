package br.com.ascence.anotei.ui.screens.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screencomponents.shared.noteoptions.NoteOptionsBar
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
            Text("Nova nota")
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
        NoteScreen()
    }
}