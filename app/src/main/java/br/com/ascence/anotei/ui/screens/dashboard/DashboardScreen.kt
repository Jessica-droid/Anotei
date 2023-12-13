package br.com.ascence.anotei.ui.screens.dashboard

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementations.NotesRepositoryImp
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.navigation.NOTE_RESULT_CREATED_OR_UPDATED
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NewNoteActivityResultContract
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.ui.screens.notelist.NotesListScreen
import br.com.ascence.anotei.ui.screens.dashboard.components.DashAppBar
import br.com.ascence.anotei.ui.screens.dashboard.components.DashNoteOptionsBar
import br.com.ascence.anotei.ui.screens.dashboard.components.NewNoteButton
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun DashboardScreen() {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        DashboardViewModel(repository)
    }

    val state by viewModel.uiState.collectAsState()
    val showNoteOptions = remember { mutableStateOf(false) }

    val newNoteActivity = rememberLauncherForActivityResult(
        contract = NewNoteActivityResultContract(),
        onResult = { result ->
            when (result) {
                NOTE_RESULT_CREATED_OR_UPDATED -> viewModel.fetchNotes()
                NOTE_RESULT_NOTHING -> println(">>>>>>>> NOTHING")
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchNotes()
    }

    DashBoardContent(
        notesList = state.notesList,
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
            newNoteActivity.launch(NoteType.UPDATE_NOTE)
        }
    )
}

@Composable
private fun DashBoardContent(
    showNoteOptions: Boolean,
    options: List<NoteOption>,
    notesList: List<Note>,
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
            notesList = notesList,
            onNoteClick = onNoteClick,
            onBackPressed = onBackPressed,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@ColorSchemePreviews
@Composable
fun DashboardPreview() {
    AnoteiTheme {
        DashBoardContent(
            notesList = emptyList(),
            showNoteOptions = false,
            options = emptyList(),
            onNoteClick = { _, _ -> },
            onNewNoteClick = {},
            onBackPressed = {}
        )
    }
}