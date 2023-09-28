package br.com.ascence.anotei.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

enum class NoteStatusPresentation(
    val icon: ImageVector,
    val iconColor: @Composable () -> Color,
    val contentDescription: String,
) {
    SCHEDULED(
        icon = Icons.Default.AlarmOn,
        iconColor = { AnoteiAppTheme.colors.menuColor },
        contentDescription = "Nota agendada"
    ),
    PROTECTED(
        icon = Icons.Default.Lock,
        iconColor = { AnoteiAppTheme.colors.lockColor },
        contentDescription = "Nota protegida"
    )
}