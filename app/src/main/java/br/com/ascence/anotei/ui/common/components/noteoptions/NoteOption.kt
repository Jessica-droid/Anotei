package br.com.ascence.anotei.ui.common.components.noteoptions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.common.components.noteoptions.preview.NoteOptionPreviewParams
import br.com.ascence.anotei.ui.common.components.noteoptions.preview.NoteOptionPreviewProvider
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NoteOption(
    option: NoteOption,
    showCheckBadge: Boolean,
    onClick: (NoteOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        IconButton(onClick = { onClick(option) }) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.optionContentDescription,
                tint = option.iconColor()
            )
        }
        option.checkContentDescription?.let {
            if (showCheckBadge) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = option.checkContentDescription,
                    tint = AnoteiAppTheme.colors.colorScheme.secondary,
                    modifier = Modifier
                        .padding(
                            bottom = AnoteiAppTheme.spaces.xSmall,
                            end = AnoteiAppTheme.spaces.xSmall
                        )
                        .size(AnoteiAppTheme.spaces.small)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NoteOptionPreview(
    @PreviewParameter(NoteOptionPreviewProvider::class) params: NoteOptionPreviewParams
){
    AnoteiTheme {
        Column(
            modifier = Modifier.background(AnoteiAppTheme.colors.colorScheme.background)
        ) {
            NoteOption(
                option = params.option,
                showCheckBadge = params.showBadge,
                onClick = {},
            )
        }
    }
}