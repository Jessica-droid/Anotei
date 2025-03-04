package br.com.ascence.anotei.ui.screens.dashboard

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.ascence.anotei.data.local.AnoteiDatabase
import br.com.ascence.anotei.data.local.implementations.NotesRepositoryImp
import br.com.ascence.anotei.data.mock.notesListMock
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.navigation.CScreens
import br.com.ascence.anotei.navigation.NOTE_RESULT_CREATED_OR_UPDATED
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.NOTE_TYPE_EXTRA
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NewNoteActivityResultContract
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.navigation.extensions.navigateToScreen
import br.com.ascence.anotei.ui.common.components.popup.AppPopup
import br.com.ascence.anotei.ui.common.components.popup.contents.NoteCategorySelection
import br.com.ascence.anotei.ui.screens.notelist.NotesListScreen
import br.com.ascence.anotei.ui.screens.dashboard.components.DashAppBar
import br.com.ascence.anotei.ui.screens.dashboard.components.DashNoteOptionsBar
import br.com.ascence.anotei.ui.screens.dashboard.components.NewNoteButton
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun DashboardScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember {
        DashboardViewModel(repository)
    }

    val state by viewModel.uiState.collectAsState()

    val noteScreen = rememberLauncherForActivityResult(
        contract = NewNoteActivityResultContract(
            note = state.selectedNoteList.firstOrNull() as? Note.TextNote
        ),
        onResult = { result ->
            when (result) {
                NOTE_RESULT_CREATED_OR_UPDATED -> {
                    viewModel.fetchNotes()
                    viewModel.resetListScrollState()
                }

                NOTE_RESULT_NOTHING -> println(">>>>>>>> NOTHING")
            }
        }
    )

    BackHandler(
        enabled = state.selectedNoteList.isNotEmpty()
    ) {
        viewModel.resetScreenState()
    }

    LaunchedEffect(key1 = state.notesList) {
        viewModel.fetchNotes()
    }

    LaunchedEffect(key1 = navBackStackEntry.savedStateHandle) {
        navBackStackEntry.savedStateHandle.get<String>(NOTE_TYPE_EXTRA)?.let { result ->
            println(">>>>>> SCREEN RESULT $result") // TODO SETUP SCREEN RESULT
        }
    }

    DashBoardContent(
        notesList = state.notesList,
        showNoteOptions = state.showNoteOptions,
        selectedNoteList = state.selectedNoteList,
        isNoteSelectionActivated = state.isSelectionModeActivated,
        onNoteClick = { note ->
            if (state.isSelectionModeActivated) {
                viewModel.updateNoteSelection(note)
            } else {
                navController.navigateToScreen(
                    screen = CScreens.NOTE_DISPLAY,
                    args = listOf(note.id.toString(), NoteType.DISPLAY_NOTE.name)
                )
            }
        },
        onNoteSelection = { note ->
            viewModel.toggleSelectionMode(true)
            viewModel.updateNoteSelection(note)
        },
        onNewNoteClick = {
            navController.navigateToScreen(
                screen = CScreens.NOTE_DISPLAY,
                args = listOf(NoteType.NEW_NOTE.name)
            )
        },
        showCategoryPopup = state.showCategoryPopup,
        onNoteCategorySelected = { category ->
            viewModel.updateSelectedNoteCategory(category)
        },
        onDismissCategoryPopup = { viewModel.updateCategoryPopupVisibility(false) },
        shouldResetListScroll = state.shouldResetListScroll,
        onNoteOptionsClick = { option -> viewModel.handleNoteOptionClick(option) },
    )
}

@Composable
private fun DashBoardContent(
    showNoteOptions: Boolean,
    isNoteSelectionActivated: Boolean,
    notesList: List<Note>,
    selectedNoteList: List<Note>,
    onNoteClick: (Note) -> Unit,
    onNoteSelection: (Note) -> Unit,
    onNoteOptionsClick: (NoteOption) -> Unit,
    onNewNoteClick: () -> Unit,
    showCategoryPopup: Boolean,
    onNoteCategorySelected: (Category) -> Unit,
    onDismissCategoryPopup: () -> Unit,
    shouldResetListScroll: Boolean,
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
                isSelectionModeActivated = isNoteSelectionActivated,
                selectedNotes = selectedNoteList,
                onFABClick = {},
                onOptionClick = onNoteOptionsClick
            )
        }
    ) { innerPadding ->
        Box(
            propagateMinConstraints = true,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            AppPopup(
                isPopupVisible = showCategoryPopup,
                onDismissRequest = onDismissCategoryPopup,
                content = { states, tOrigin ->
                    NoteCategorySelection(
                        expandedStates = states,
                        transformOrigin = tOrigin,
                        onCategorySelected = { category -> onNoteCategorySelected(category) }
                    )
                }
            )

            NotesListScreen(
                notesList = notesList,
                onNoteClick = onNoteClick,
                onNoteSelection = onNoteSelection,
                selectedNotesList = selectedNoteList,
                shouldResetScroll = shouldResetListScroll,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ColorSchemePreviews
@Composable
fun DashboardPreview() {
    AnoteiTheme {
        DashBoardContent(
            notesList = notesListMock,
            showNoteOptions = false,
            isNoteSelectionActivated = false,
            selectedNoteList = emptyList(),
            onNoteClick = { _ -> },
            onNoteSelection = { _ -> },
            onNewNoteClick = {},
            showCategoryPopup = false,
            onNoteCategorySelected = {},
            onDismissCategoryPopup = {},
            shouldResetListScroll = false,
            onNoteOptionsClick = {}
        )
    }
}

@ColorSchemePreviews
@Composable
fun DashboardEmptyStatePreview() {
    AnoteiTheme {
        DashBoardContent(
            notesList = emptyList(),
            showNoteOptions = false,
            isNoteSelectionActivated = false,
            selectedNoteList = emptyList(),
            onNoteClick = { _ -> },
            onNoteSelection = { _ -> },
            onNewNoteClick = {},
            showCategoryPopup = false,
            onNoteCategorySelected = {},
            onDismissCategoryPopup = {},
            shouldResetListScroll = false,
            onNoteOptionsClick = {}
        )
    }
}