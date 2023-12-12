package br.com.ascence.anotei.ui.screens.note.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun SimpleDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    confirmLabel: String,
    onDismiss: () -> Unit,
    dismissLabel: String? = null,
) {
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = message) },
        titleContentColor = AnoteiAppTheme.colors.primaryTextColor,
        textContentColor = AnoteiAppTheme.colors.secondaryTextColor,
        onDismissRequest = onDismiss,
        dismissButton = {
            dismissLabel?.let { actionLabel ->
                TextButton(
                    content = { Text(actionLabel, color = AnoteiAppTheme.colors.accentColor) },
                    onClick = onDismiss
                )
            }
        },
        confirmButton = {
            TextButton(
                content = { Text(confirmLabel, color = AnoteiAppTheme.colors.accentColor) },
                onClick = onConfirm
            )
        },
        containerColor = AnoteiAppTheme.colors.secondaryBackgroundColor,
    )
}

@Composable
@ColorSchemePreviews
private fun SimpleAlertDialogWithDismissPreview() {
    AnoteiTheme {
        SimpleDialog(
            title = "Descartar nota?",
            message = "Você deseja descartar o que anotou até agora?",
            confirmLabel = "Confirmar",
            dismissLabel = "Cancelar",
            onDismiss = {},
            onConfirm = {}
        )
    }
}

@Composable
@ColorSchemePreviews
private fun SimpleAlertDialogWithoutDismissPreview() {
    AnoteiTheme {
        SimpleDialog(
            title = "Um momento!",
            message = "Anote alguma coisa antes de salvar.",
            confirmLabel = "Confirmar",
            onDismiss = {},
            onConfirm = {}
        )
    }
}