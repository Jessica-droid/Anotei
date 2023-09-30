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
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            CreateNoteFAB(showButton = showNoteOptions.value.not())
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            NoteOptions(showBottomBar = showNoteOptions.value)
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
private fun CreateNoteFAB(showButton: Boolean) =
    AnimatedVisibility(
        visible = showButton,
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        FloatingActionButton(
            containerColor = AnoteiAppTheme.colors.colorScheme.primary,
            onClick = { } // TODO setup onClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Criar anotação", // TODO replace this type of string
                tint = AnoteiAppTheme.colors.primaryButtonTextColor
            )
        }
    }

@Composable
private fun NoteOptions(showBottomBar: Boolean) {
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
        BottomAppBar(
            containerColor = AnoteiAppTheme.colors.colorScheme.background,
            actions = {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Adjust,
                        contentDescription = "Categoria da anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Agendar anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Proteger anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = AnoteiAppTheme.colors.colorScheme.primary,
                    onClick = { } // TODO setup onClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Alterar anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.primaryButtonTextColor
                    )
                }
            }
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