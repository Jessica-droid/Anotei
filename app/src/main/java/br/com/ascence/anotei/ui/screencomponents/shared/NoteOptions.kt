package br.com.ascence.anotei.ui.screencomponents.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

const val FAB_ICON_TEST_TAG = "NoteOptionFabIcon"

@Composable
fun NoteOptions(
    onCategoryClick: () -> Unit,
    onSchedulerClick: () -> Unit,
    onLockClick: () -> Unit,
    onFABClick: () -> Unit,
    onRemoveClick: () -> Unit,
    optionType: NoteOptionsPresentationType,
) {
    BottomAppBar(
        containerColor = AnoteiAppTheme.colors.bottomBarColor,
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

            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Apagaranotação", // TODO replace this type of string
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

@Composable
fun OptionMainAction(
    optionType: NoteOptionsPresentationType,
    onFABClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        containerColor = AnoteiAppTheme.colors.bottomBarFabColor,
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
}

@ColorSchemePreviews
@Composable
private fun NoteOptionPreviewLight() {
    AnoteiTheme {
        NoteOptions(
            onCategoryClick = {},
            onSchedulerClick = {},
            onLockClick = {},
            onFABClick = {},
            onRemoveClick = {},
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
        )
    }
}