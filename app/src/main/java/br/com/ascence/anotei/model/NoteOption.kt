package br.com.ascence.anotei.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

sealed class NoteOption {
    abstract val icon: ImageVector
    abstract val iconColor: @Composable () -> Color
    abstract val optionContentDescription: String
    abstract val checkContentDescription: String?

    class Category(
        override val icon: ImageVector = Icons.Default.Brightness1,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val optionContentDescription: String = "Selecionar categoria da nota",
        override val checkContentDescription: String? = null,
    ) : NoteOption()

    class Schedule(
        override val icon: ImageVector = Icons.Default.Schedule,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val optionContentDescription: String = "Agendar nota",
        override val checkContentDescription: String? = "Esta nota está agendada",
    ) : NoteOption()

    class Protect(
        override val icon: ImageVector = Icons.Default.Lock,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val optionContentDescription: String = "Proteger nota",
        override val checkContentDescription: String? = "Esta nota está protegida por senha",
    ) : NoteOption()

    class Delete(
        override val icon: ImageVector = Icons.Default.Delete,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val optionContentDescription: String = "Apagar nota",
        override val checkContentDescription: String? = null,
    ) : NoteOption()
}