package br.com.ascence.anotei.ui.screens.dashboard

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.screens.note.NoteActivity
import br.com.ascence.anotei.ui.screens.notelist.NotesListScreen
import br.com.ascence.anotei.ui.screens.dashboard.components.DashAppBar
import br.com.ascence.anotei.ui.screens.dashboard.components.DashNoteOptionsBar
import br.com.ascence.anotei.ui.screens.dashboard.components.NewNoteButton
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun DashboardScreen() {
    val viewModel = remember {
        DashboardViewModel()
    }

    val state by viewModel.uiState.collectAsState()
    val showNoteOptions = remember { mutableStateOf(false) }
    val context = LocalContext.current

    DashBoardContent(
        showNoteOptions = showNoteOptions.value,
        options = state.noteOptions,
        onNoteClick = { note, haveSelectedNote ->
            viewModel.setupNoteOptions(note)
            showNoteOptions.value = haveSelectedNote
        },
        onBackPressed = { haveSelectedNote ->
            showNoteOptions.value = haveSelectedNote
        },
        onNewNoteClick = {
            context.startActivity(Intent(context, NoteActivity::class.java))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashBoardContent(
    showNoteOptions: Boolean,
    options: List<NoteOption>,
    onNoteClick: (Note, Boolean) -> Unit,
    onBackPressed: (Boolean) -> Unit,
    onNewNoteClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { DashAppBar() },
        floatingActionButton = {
            NewNoteButton(
                showButton = showNoteOptions.not(),
                onFabClick = onNewNoteClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            DashNoteOptionsBar(
                showBottomBar = showNoteOptions,
                options = options,
                onFABClick = {} // TODO setup note Edit,
            )
        }
    ) { innerPadding ->
        NotesListScreen(
            onNoteClick = onNoteClick,
            onBackPressed = onBackPressed,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@ColorSchemePreviews
@Composable
fun DashboardPreview() {
    AnoteiTheme {
        DashBoardContent(
            showNoteOptions = false,
            options = emptyList(),
            onNoteClick = { _, _ -> },
            onNewNoteClick = {},
            onBackPressed = {}
        )
    }
}