package br.com.ascence.anotei.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.ascence.anotei.extension.getCategoryColor
import br.com.ascence.anotei.extension.getProtectionIconColor
import br.com.ascence.anotei.extension.getSchedulerIconColor
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

sealed class NoteOption {
    abstract val icon: ImageVector
    abstract val iconColor: @Composable () -> Color
    abstract val contentDescription: String
    abstract val action: () -> Unit

    class Category(
        override val icon: ImageVector = Icons.Default.Brightness1,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val contentDescription: String = "Selecionar categoria da nota",
        override val action: () -> Unit,
    ) : NoteOption()

    class Schedule(
        override val icon: ImageVector = Icons.Default.Schedule,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val contentDescription: String = "Agendar nota",
        override val action: () -> Unit,
    ) : NoteOption()

    class Protect(
        override val icon: ImageVector = Icons.Default.Lock,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val contentDescription: String = "Proteger nota",
        override val action: () -> Unit,
    ) : NoteOption()

    class Delete(
        override val icon: ImageVector = Icons.Default.Delete,
        override val iconColor: @Composable () -> Color = { AnoteiAppTheme.colors.menuColor },
        override val contentDescription: String = "Apagar nota",
        override val action: () -> Unit,
    ) : NoteOption()

    companion object {

        fun getAllOptions(
            note: Note,
            onCategoryClick: () -> Unit,
            onScheduleClick: () -> Unit,
            onProtectClick: () -> Unit,
            onDeleteClick: () -> Unit,
        ): List<NoteOption> = listOf(
            Category(
                action = onCategoryClick,
                iconColor = { note.getCategoryColor() }
            ),
            Schedule(
                action = onScheduleClick,
                iconColor = { note.getSchedulerIconColor() }
            ),
            Protect(
                action = onProtectClick,
                iconColor = { note.getProtectionIconColor() }
            ),
            Delete(
                action = onDeleteClick
            ),
        )
    }
}