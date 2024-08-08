package br.com.ascence.anotei.ui.screens.noteDisplay

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import br.com.ascence.anotei.data.preview.mock.fakeTextNote
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.ui.utils.TRANSITION_SCREEN_ANIMATION_DURATION
import br.com.ascence.anotei.ui.screens.noteDisplay.components.NoteHeaderDisplay
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.ui.utils.modifyIfNotNull
import br.com.ascence.anotei.utils.VERY_LONG_NOTE_CONTENT
import br.com.ascence.anotei.utils.date.DateHelper

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.NoteDisplay(
    noteId: String?,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val contextCompat = LocalContext.current

    val db = AnoteiDatabase.getDatabase(context = contextCompat)
    val repository = NotesRepositoryImp(db.noteDao())

    val viewModel = remember { NoteDisplayViewModel(repository) }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        noteId?.let {
            viewModel.getNoteToDisplay(noteId = it)
        } ?: println(">>>>>> Não foi possível recuperar nota, ID inválido.")
    }

    TextNoteDisplayContent(
        note = state.note as? Note.TextNote,
        animatedVisibilityScope = animatedVisibilityScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.TextNoteDisplayContent(
    note: Note.TextNote?,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
) {
    note?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AnoteiAppTheme.colors.colorScheme.background)
                .modifyIfNotNull(animatedVisibilityScope) { scope ->
                    sharedElement(
                        state = rememberSharedContentState(key = "card/${note.id}"),
                        animatedVisibilityScope = scope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = TRANSITION_SCREEN_ANIMATION_DURATION)
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(AnoteiAppTheme.spaces.medium)
                    .verticalScroll(rememberScrollState())
            ) {
                NoteHeaderDisplay(
                    categoryColor = note.category.getColor(),
                    title = note.title,
                    creationDate = DateHelper().formatDateToString(note.creationDate),
                    noteId = note.id.toString(),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                Text(
                    text = note.description,
                    fontSize = AnoteiAppTheme.fontSizes.medium,
                    color = AnoteiAppTheme.colors.secondaryTextColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = AnoteiAppTheme.spaces.medium)
                        .modifyIfNotNull(animatedVisibilityScope) { scope ->
                            sharedElement(
                                state = rememberSharedContentState(key = "description/${note.id}"),
                                animatedVisibilityScope = scope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = TRANSITION_SCREEN_ANIMATION_DURATION)
                                }
                            )
                        }
                )
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Selecione uma nota",
            color = AnoteiAppTheme.colors.primaryTextColor,
            fontSize = AnoteiAppTheme.fontSizes.xLarge
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ColorSchemePreviews
@Composable
private fun NoteDisplayPreview() {
    AnoteiTheme {
        SharedTransitionLayout {
            TextNoteDisplayContent(
                fakeTextNote.copy(
                    description = VERY_LONG_NOTE_CONTENT,
                ),
            )
        }
    }
}