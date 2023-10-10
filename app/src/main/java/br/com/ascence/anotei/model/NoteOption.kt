package br.com.ascence.anotei.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

data class NoteOption(
    val icon: ImageVector,
    val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
    val contentDescription: String,
    val action: () -> Unit,
)