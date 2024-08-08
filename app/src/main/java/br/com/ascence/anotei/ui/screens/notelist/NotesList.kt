package br.com.ascence.anotei.ui.screens.notelist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.ascence.anotei.data.mock.notesListMock
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.ui.screens.notelist.components.notecard.NoteCard
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NotesListScreen(
    notesList: List<Note>,
    selectedNotesList: List<Note>,
    canExpandCard: Boolean,
    onNoteClick: (Note) -> Unit,
    onNoteSelection: (Note) -> Unit,
    shouldResetScroll: Boolean,
    animationScope: AnimatedVisibilityScope?,
    modifier: Modifier = Modifier,
) {
    ListContent(
        notes = notesList,
        selectedNotesList = selectedNotesList,
        canExpandCard = canExpandCard,
        shouldResetScroll = shouldResetScroll,
        onNoteClick = { note -> onNoteClick(note) },
        onNoteSelection = { note -> onNoteSelection(note) },
        animationScope = animationScope,
        modifier = modifier,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.ListContent(
    notes: List<Note>,
    selectedNotesList: List<Note>,
    canExpandCard: Boolean,
    onNoteClick: (Note) -> Unit,
    onNoteSelection: (Note) -> Unit,
    shouldResetScroll: Boolean,
    animationScope: AnimatedVisibilityScope? = null,
    modifier: Modifier = Modifier,
) {
    if (notes.isNotEmpty()) {
        Notes(
            notes = notes,
            onNoteClick = onNoteClick,
            onNoteSelection = onNoteSelection,
            selectedNotesList = selectedNotesList,
            canExpandCard = canExpandCard,
            shouldResetScroll = shouldResetScroll,
            animationScope = animationScope,
            modifier = modifier,
        )
    } else {
        NotesEmptyState(
            modifier = modifier
                .fillMaxSize()
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Notes(
    notes: List<Note>,
    selectedNotesList: List<Note>,
    canExpandCard: Boolean,
    onNoteClick: (Note) -> Unit,
    onNoteSelection: (Note) -> Unit,
    shouldResetScroll: Boolean,
    animationScope: AnimatedVisibilityScope?,
    modifier: Modifier = Modifier,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(shouldResetScroll) {
        if (shouldResetScroll) {
            listState.scrollToItem(index = 0)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.background(color = AnoteiAppTheme.colors.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(AnoteiAppTheme.spaces.xSmall),
    ) {
        items(
            notes,
            key = { note -> note.id }
        ) { note ->
            NoteCard(
                note = note,
                onCardClick = onNoteClick,
                onCardSelection = onNoteSelection,
                isCardSelected = selectedNotesList.any { it.id == note.id },
                shouldExpandCard = canExpandCard,
                animationScope = animationScope,
                modifier = modifier
            )
        }
        item {
            Spacer(modifier = modifier.height(AnoteiAppTheme.spaces.medium))
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ColorSchemePreviews
@Composable
private fun NotesListPreview() {
    AnoteiTheme {
        SharedTransitionLayout {
            ListContent(
                notes = notesListMock,
                selectedNotesList = emptyList(),
                canExpandCard = false,
                onNoteClick = {},
                onNoteSelection = {},
                shouldResetScroll = false
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ColorSchemePreviews
@Composable
private fun NotesListEmptyPreview() {
    AnoteiTheme {
        SharedTransitionLayout {
            ListContent(
                notes = emptyList(),
                selectedNotesList = emptyList(),
                canExpandCard = false,
                onNoteClick = {},
                onNoteSelection = {},
                shouldResetScroll = false
            )
        }
    }
}