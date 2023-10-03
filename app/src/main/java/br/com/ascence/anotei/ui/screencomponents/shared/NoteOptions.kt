package br.com.ascence.anotei.ui.screencomponents.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.model.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

const val FAB_ICON_TEST_TAG = "NoteOptionFabIcon"

@Composable
fun NoteOptions(
    showBottomBar: Boolean,
    onCategoryClick: () -> Unit,
    onSchedulerClick: () -> Unit,
    onLockClick: () -> Unit,
    onFABClick: () -> Unit,
    optionType: NoteOptionsPresentationType,
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
        BottomAppBar(
            containerColor = AnoteiAppTheme.colors.colorScheme.background,
            actions = {

                IconButton(onClick = onCategoryClick) {
                    Icon(
                        imageVector = Icons.Default.Adjust,
                        contentDescription = "Categoria da anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }

                IconButton(onClick = onSchedulerClick) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Agendar anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }

                IconButton(onClick = onLockClick) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Proteger anotação", // TODO replace this type of string
                        tint = AnoteiAppTheme.colors.menuColor
                    )
                }
            },
            floatingActionButton = {
                OptionMainAction(
                    optionType = optionType,
                    onFABClick = onFABClick
                )
            }
        )
    }
}

@Composable
fun OptionMainAction(
    optionType: NoteOptionsPresentationType,
    onFABClick: () -> Unit,
    modifier: Modifier = Modifier,
) =
    FloatingActionButton(
        containerColor = AnoteiAppTheme.colors.colorScheme.primary,
        onClick = onFABClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = optionType.fabIcon,
            contentDescription = optionType.fabContentDescription,
            tint = optionType.fabIconColor(),
            modifier = Modifier.testTag(FAB_ICON_TEST_TAG)
        )
    }

@Preview
@Composable
private fun NoteOptionPreviewLight() {
    AnoteiTheme(darkTheme = false) {
        NoteOptions(
            showBottomBar = true,
            onCategoryClick = {},
            onSchedulerClick = {},
            onLockClick = {},
            onFABClick = {},
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
        )
    }
}

@Preview
@Composable
private fun NoteOptionPreviewDark() {
    AnoteiTheme(darkTheme = true) {
        NoteOptions(
            showBottomBar = true,
            onCategoryClick = {},
            onSchedulerClick = {},
            onLockClick = {},
            onFABClick = {},
            optionType = NoteOptionsPresentationType.EDIT_MODE
        )
    }
}