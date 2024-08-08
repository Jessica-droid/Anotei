package br.com.ascence.anotei.ui.screens.noteDisplay.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import br.com.ascence.anotei.ui.utils.TRANSITION_SCREEN_ANIMATION_DURATION
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.utils.modifyIfNotNull

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.NoteHeaderDisplay(
    categoryColor: Color,
    title: String,
    creationDate: String,
    noteId: String,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Brightness1,
                tint = categoryColor,
                contentDescription = "Cor da categoria",
                modifier = Modifier
                    .size(AnoteiAppTheme.spaces.xxxLarge)
                    .modifyIfNotNull(animatedVisibilityScope) { scope ->
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
                color = AnoteiAppTheme.colors.primaryTextColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = AnoteiAppTheme.fontSizes.xLarge,
                modifier = Modifier
                    .padding(horizontal = AnoteiAppTheme.spaces.medium)
                    .modifyIfNotNull(animatedVisibilityScope) { scope ->
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
        Text(
            text = creationDate,
            fontSize = AnoteiAppTheme.fontSizes.small,
            color = AnoteiAppTheme.colors.accentColor,
            modifier = Modifier
                .padding(top = AnoteiAppTheme.spaces.xSmall)
                .modifyIfNotNull(animatedVisibilityScope) { scope ->
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
}