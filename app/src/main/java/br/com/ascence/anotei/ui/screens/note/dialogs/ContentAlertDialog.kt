package br.com.ascence.anotei.ui.screens.note.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun ContentAlertDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                content = { Text("Confirmar", color = AnoteiAppTheme.colors.accentColor) },
                onClick = onConfirm
            )
        },
        title = { Text(text = "Descartar nota?") },
        text = { Text("Você deseja descartar o que anotou até agora?") },
        dismissButton = {
            TextButton(
                content = { Text("Cancelar", color = AnoteiAppTheme.colors.accentColor) },
                onClick = onDismiss
            )
        },
        containerColor = AnoteiAppTheme.colors.secondaryBackgroundColor,
        titleContentColor = AnoteiAppTheme.colors.primaryTextColor,
        textContentColor = AnoteiAppTheme.colors.secondaryTextColor
    )
}

@Composable
@ColorSchemePreviews
private fun ContentAlertDialogPreview() {
    AnoteiTheme {
        ContentAlertDialog(onDismiss = {}, onConfirm = {})
    }
}