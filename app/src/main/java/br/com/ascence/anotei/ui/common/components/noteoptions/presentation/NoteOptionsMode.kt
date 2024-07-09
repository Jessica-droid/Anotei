package br.com.ascence.anotei.ui.common.components.noteoptions.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

internal enum class NoteOptionsMode(
    val fabIcon: ImageVector,
    val fabIconColor: @Composable () -> Color = { AnoteiAppTheme.colors.secondaryBackgroundColor },
    val fabContentDescription: String,
) {

    CREATE_MODE (
        fabIcon = Icons.Default.Check,
        fabContentDescription = "Criar nota"
    ),
    EDIT_MODE(
        fabIcon = Icons.Default.Check,
        fabContentDescription = "Confirmar alterações"
    ),
    PREVIEW_MODE(
        fabIcon = Icons.Default.Edit,
        fabContentDescription = "Alterar nota"
    ),
}