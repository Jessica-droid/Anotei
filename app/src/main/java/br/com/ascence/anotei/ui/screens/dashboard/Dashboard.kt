package br.com.ascence.anotei.ui.screens.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.model.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.screencomponents.shared.NoteOptions
import br.com.ascence.anotei.ui.screens.notes.NotesListScreen
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {

    val showNoteOptions = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() },
        floatingActionButton = {
            CreateNoteFAB(
                showButton = showNoteOptions.value.not(),
                onFabClick = {} // TODO setup note creation
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            NoteOptionsBar(
                showBottomBar = showNoteOptions.value,
                onCategoryClick = {}, // TODO setup category update
                onSchedulerClick = {}, // TODO setup scheduling update
                onLockClick = {}, // TODO setup lock state update
                onFABClick = {}, // TODO setup note edit
            )
        }
    ) { innerPadding ->
        NotesListScreen(
            onNoteClick = { haveSelectedNote ->
                showNoteOptions.value = haveSelectedNote
            },
            onBackPressed = { haveSelectedNote ->
                showNoteOptions.value = haveSelectedNote
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() =
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CreateNoteFAB(
    showButton: Boolean,
    onFabClick: () -> Unit,
) =
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
                tint = AnoteiAppTheme.colors.primaryButtonTextColor
            )
        }
    }

@Composable
private fun NoteOptionsBar(
    showBottomBar: Boolean,
    onCategoryClick: () -> Unit,
    onSchedulerClick: () -> Unit,
    onLockClick: () -> Unit,
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
        NoteOptions(
            onCategoryClick = onCategoryClick,
            onSchedulerClick = onSchedulerClick,
            onLockClick = onLockClick,
            onFABClick = onFABClick,
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreviewLight() {
    AnoteiTheme(darkTheme = false) {
        Dashboard()
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreviewDark() {
    AnoteiTheme(darkTheme = true) {
        Dashboard()
    }
}