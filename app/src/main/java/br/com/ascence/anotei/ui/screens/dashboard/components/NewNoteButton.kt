package br.com.ascence.anotei.ui.screens.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@Composable
fun NewNoteButton(
    showButton: Boolean,
    onFabClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = showButton,
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        FloatingActionButton(
            containerColor = AnoteiAppTheme.colors.colorScheme.primary,
            onClick = onFabClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Criar anotação", // TODO replace this type of string
                tint = AnoteiAppTheme.colors.secondaryBackgroundColor
            )
        }
    }
}