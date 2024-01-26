package br.com.ascence.anotei.ui.screens.dashboard

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

private const val OUT_OF_RANGE_ID = -1

@Composable
fun DashboardScreen() {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        DashboardViewModel(repository)
    }

    val state by viewModel.uiState.collectAsState()
    val showNoteOptions = state.showNoteOptions

    val noteScreen = rememberLauncherForActivityResult(
        contract = NewNoteActivityResultContract(note = state.selectedNote as? Note.TextNote),
        onResult = { result ->
            when (result) {
                NOTE_RESULT_CREATED_OR_UPDATED -> viewModel.fetchNotes()
                NOTE_RESULT_NOTHING -> println(">>>>>>>> NOTHING")
            }
        }
    )

    BackHandler(
        enabled = state.selectedNote != null
    ) {
        viewModel.updateNoteSelection(null)
        viewModel.updateOptionsVisibility(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchNotes()
    }

    LaunchedEffect(state.selectedNote) {
        viewModel.setupNoteOptions()
    }

    DashBoardContent(
        notesList = state.notesList,
        showNoteOptions = showNoteOptions,
        options = state.noteOptions,
        selectedNoteId = state.selectedNote?.id ?: OUT_OF_RANGE_ID,
        onNoteClick = { note ->
            viewModel.updateNoteSelection(note)
            viewModel.updateOptionsVisibility(showOptions = true)
        },
        onNewNoteClick = {
            noteScreen.launch(NoteType.NEW_NOTE)
        },
        onAlterNoteClick = {
            noteScreen.launch(NoteType.UPDATE_NOTE)
            viewModel.updateNoteSelection(null)
            viewModel.updateOptionsVisibility(showOptions = false)
        }
    )
}

@Composable
private fun DashBoardContent(
    showNoteOptions: Boolean,
    options: List<NoteOption>,
    notesList: List<Note>,
    selectedNoteId: Int,
    onNoteClick: (Note) -> Unit,
    onNewNoteClick: () -> Unit,
    onAlterNoteClick: () -> Unit,
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
                onFABClick = onAlterNoteClick
            )
        }
    ) { innerPadding ->
        NotesListScreen(
            notesList = notesList,
            onNoteClick = onNoteClick,
            selectedNoteId = selectedNoteId,
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
            selectedNoteId = OUT_OF_RANGE_ID,
            onNoteClick = { _ -> },
            onNewNoteClick = {},
            onAlterNoteClick = {}
        )
    }
}