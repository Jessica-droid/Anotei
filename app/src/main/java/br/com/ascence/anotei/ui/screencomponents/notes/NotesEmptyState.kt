package br.com.ascence.anotei.ui.screencomponents.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
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

@ColorSchemePreviews
@Composable
private fun EmptyStatePreview() {
    AnoteiTheme {
        NotesEmptyState(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}