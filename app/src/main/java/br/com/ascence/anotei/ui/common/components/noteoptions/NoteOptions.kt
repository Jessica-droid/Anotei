package br.com.ascence.anotei.ui.common.components.noteoptions

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.noteOptionsPreview
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.presentation.NoteOptionsPresentationType
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
                Option(option)
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

class NoteOptionsBarPreviewProvider : PreviewParameterProvider<NoteOptionsPresentationType> {
    override val values: Sequence<NoteOptionsPresentationType>
        get() = sequenceOf(
            NoteOptionsPresentationType.PREVIEW_MODE,
            NoteOptionsPresentationType.EDIT_MODE
        )
}

@ColorSchemePreviews
@Composable
private fun NoteOptionPreviewLight(
    @PreviewParameter(NoteOptionsBarPreviewProvider::class) type: NoteOptionsPresentationType,
) {
    AnoteiTheme {
        NoteOptionsBar(
            options = noteOptionsPreview,
            onFABClick = {},
            optionType = type
        )
    }
}