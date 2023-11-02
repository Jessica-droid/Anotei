package br.com.ascence.anotei.ui.common.components.noteoptions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@Composable
fun Option(option: NoteOption) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = option.action) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.optionContentDescription,
                tint = option.iconColor()
            )
        }
        option.checkContentDescription?.let {
            if (option.showBadge) {
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