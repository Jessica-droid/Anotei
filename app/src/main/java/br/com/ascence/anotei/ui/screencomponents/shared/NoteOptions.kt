package br.com.ascence.anotei.ui.screencomponents.shared

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

const val FAB_ICON_TEST_TAG = "NoteOptionFabIcon"

@Composable
fun NoteOptionsBar(
    options: List<NoteOption>,
    onFABClick: () -> Unit,
    optionType: NoteOptionsPresentationType,
) {
    BottomAppBar(
        containerColor = AnoteiAppTheme.colors.bottomBarColor,
        actions = {

            options.map { option ->
                IconButton(onClick = option.action) {
                    Icon(
                        imageVector = option.icon,
                        contentDescription = option.contentDescription,
                        tint = option.iconColor()
                    )
                }
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
        NoteOptionsBar(
            options = noteOptionsPreview,
            onFABClick = {},
            optionType = NoteOptionsPresentationType.PREVIEW_MODE
        )
    }
}