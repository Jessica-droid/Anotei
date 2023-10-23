package br.com.ascence.anotei.ui.screens.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.navigation.NOTE_PATH
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screencomponents.notes.NotesListScreen
import br.com.ascence.anotei.ui.screencomponents.shared.noteoptions.NoteOptionsBar
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme


@Composable
fun Dashboard(
    navController: NavController,
) {
    val viewModel = remember {
        DashboardViewModel()
    }

    val state by viewModel.uiState.collectAsState()
    val showNoteOptions = remember { mutableStateOf(false) }

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
            navController.navigate(NOTE_PATH)
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
        topBar = { AppBar() },
        floatingActionButton = {
            CreateNoteButton(
                showButton = showNoteOptions.not(),
                onFabClick = onNewNoteClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            NoteBar(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Anotei", // TODO replace this type of string
                color = AnoteiAppTheme.colors.appTitleColor,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = AnoteiAppTheme.colors.colorScheme.background
        )
    )
}

@Composable
private fun CreateNoteButton(
    showButton: Boolean,
    onFabClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = showButton,
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        FloatingActionButton(
            containerColor = AnoteiAppTheme.colors.colorScheme.primary,
            onClick = onFabClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Criar anotação", // TODO replace this type of string
                tint = AnoteiAppTheme.colors.secondaryBackgroundColor
            )
        }
    }
}

@Composable
private fun NoteBar(
    showBottomBar: Boolean,
    options: List<NoteOption>,
    onFABClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = showBottomBar,
        enter = expandVertically(
            animationSpec = tween(200),
            expandFrom = Alignment.Bottom
        ),
        exit = shrinkVertically(
            animationSpec = tween(200),
            shrinkTowards = Alignment.Bottom
        )
    ) {
        NoteOptionsBar(
            onFABClick = onFABClick,
            options = options,
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
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