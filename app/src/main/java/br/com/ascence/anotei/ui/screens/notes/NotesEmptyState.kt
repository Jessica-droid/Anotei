package br.com.ascence.anotei.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NotesEmptyState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Que tal anotar isso aí?", // TODO replace this kind of string
            color = AnoteiAppTheme.colors.menuColor,
            fontWeight = FontWeight.Bold,
            fontSize = AnoteiAppTheme.fontSizes.xLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStatePreviewLight() {
    AnoteiTheme(darkTheme = false) {
        NotesEmptyState(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStatePreviewDark() {
    AnoteiTheme(darkTheme = true) {
        NotesEmptyState(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}