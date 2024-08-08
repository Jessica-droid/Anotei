package br.com.ascence.anotei.ui.screens.notelist.components.notecard

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.utils.TRANSITION_SCREEN_ANIMATION_DURATION
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import br.com.ascence.anotei.ui.utils.modifyIfNotNull

private const val HEADER_TITLE_MAX_LINES = 1

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NoteCardHeader(
    title: String,
    creationDate: String,
    categoryColor: Color,
    status: List<NoteStatusPresentation>,
    noteId: String,
    modifier: Modifier = Modifier,
    animationScope: AnimatedVisibilityScope? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TitleAndCategoryColor(
                title = title,
                color = categoryColor,
                noteId = noteId,
                animationScope = animationScope
            )
            Text(
                text = creationDate,
                color = AnoteiAppTheme.colors.tertiaryTextColor,
                fontSize = AnoteiAppTheme.fontSizes.small,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.modifyIfNotNull(animationScope) { scope ->
                    sharedElement(
                        state = rememberSharedContentState(key = "creationDate/$noteId"),
                        animatedVisibilityScope = scope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = TRANSITION_SCREEN_ANIMATION_DURATION)
                        }
                    )
                },
            )
        }
        NoteStatus(noteStatus = status)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.TitleAndCategoryColor(
    title: String,
    color: Color,
    noteId: String,
    animationScope: AnimatedVisibilityScope? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Brightness1,
            tint = color,
            contentDescription = "Cor da categoria",
            modifier = Modifier
                .size(AnoteiAppTheme.spaces.medium)
                .modifyIfNotNull(animationScope) { scope ->
                    sharedElement(
                        state = rememberSharedContentState(key = "category/$noteId"),
                        animatedVisibilityScope = scope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = TRANSITION_SCREEN_ANIMATION_DURATION)
                        }
                    )
                }
        )
        Text(
            text = title,
            fontSize = AnoteiAppTheme.fontSizes.medium,
            color = AnoteiAppTheme.colors.primaryTextColor,
            fontWeight = FontWeight.SemiBold,
            maxLines = HEADER_TITLE_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = AnoteiAppTheme.spaces.xSmall)
                .modifyIfNotNull(animationScope) { scope ->
                    sharedElement(
                        state = rememberSharedContentState(key = "title/$noteId"),
                        animatedVisibilityScope = scope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = TRANSITION_SCREEN_ANIMATION_DURATION)
                        }
                    )
                },
        )
    }
}

@Composable
private fun NoteStatus(noteStatus: List<NoteStatusPresentation>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = AnoteiAppTheme.spaces.xSmall)
    ) {
        noteStatus.map { status ->
            Icon(
                imageVector = status.icon,
                tint = status.iconColor(),
                contentDescription = status.contentDescription,
                modifier = Modifier.size(AnoteiAppTheme.spaces.large)
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@ColorSchemePreviews
@Composable
private fun NoteCardHeaderPreviewLight() {
    AnoteiTheme {
        SharedTransitionLayout {
            NoteCardHeader(
                title = "Título",
                creationDate = "25 de Setembro",
                categoryColor = AnoteiAppTheme.colors.allChipColor,
                status = listOf(NoteStatusPresentation.SCHEDULED, NoteStatusPresentation.PROTECTED),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AnoteiAppTheme.colors.colorScheme.background),
                noteId = ""
            )
        }
    }
}