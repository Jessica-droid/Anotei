package br.com.ascence.anotei.ui.screens.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NoteHeader(
    modifier: Modifier = Modifier,
) {

    val title: MutableState<String> = remember { mutableStateOf("Sem título") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(AnoteiAppTheme.colors.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.Default.Brightness1,
            tint = AnoteiAppTheme.colors.allChipColor,
            contentDescription = "Cor da categoria",
            modifier = Modifier
                .padding(end = AnoteiAppTheme.spaces.medium)
                .size(AnoteiAppTheme.spaces.xxxLarge)
        )
        Column {
            BasicTextField(
                value = title.value,
                singleLine = true,
                textStyle = TextStyle(
                    color = AnoteiAppTheme.colors.primaryTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = AnoteiAppTheme.fontSizes.xLarge,
                ),
                onValueChange = { newTitle -> title.value = newTitle },
            )
            Text(
                text = "30 de Outubro",
                fontSize = AnoteiAppTheme.fontSizes.small,
                color = AnoteiAppTheme.colors.accentColor
            )
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
        NoteHeader()
    }
}