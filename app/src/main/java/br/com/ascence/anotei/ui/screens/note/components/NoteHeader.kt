package br.com.ascence.anotei.ui.screens.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun NoteHeader(
    noteCategoryColor: Color,
    titleInitialValue: String,
    creationDateValue: String,
    onTitleChanged: (String) -> Unit,
    isNewNote: Boolean,
    titleMaxLength: String,
    onTitleDone: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        if (isNewNote) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AnoteiAppTheme.colors.colorScheme.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Brightness1,
                tint = noteCategoryColor,
                contentDescription = "Cor da categoria",
                modifier = Modifier
                    .padding(end = AnoteiAppTheme.spaces.medium)
                    .size(AnoteiAppTheme.spaces.xxxLarge)
            )

            TextField(
                value = titleInitialValue,
                onValueChange = onTitleChanged,
                textStyle = TextStyle(
                    color = AnoteiAppTheme.colors.primaryTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = AnoteiAppTheme.fontSizes.xLarge,
                ),
                placeholder = {
                    Text(
                        text = "Defina um título",
                        color = AnoteiAppTheme.colors.accentColor,
                        fontSize = AnoteiAppTheme.fontSizes.xLarge,
                        fontWeight = FontWeight.Light,
                    )
                },
                supportingText = {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${titleInitialValue.length}/$titleMaxLength",
                            color = AnoteiAppTheme.colors.bottomBarFabColor,
                            fontSize = AnoteiAppTheme.fontSizes.small,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                keyboardActions = KeyboardActions(
                    onDone = { onTitleDone() }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = AnoteiAppTheme.colors.colorScheme.background,
                    focusedContainerColor = AnoteiAppTheme.colors.colorScheme.background,
                    cursorColor = AnoteiAppTheme.colors.colorScheme.secondary,
                    focusedIndicatorColor = AnoteiAppTheme.colors.bottomBarFabColor,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
        }
        Text(
            text = creationDateValue,
            fontSize = AnoteiAppTheme.fontSizes.small,
            color = AnoteiAppTheme.colors.accentColor,
            modifier = Modifier.padding(top = AnoteiAppTheme.spaces.xSmall)
        )
    }
}

@ColorSchemePreviews
@Composable
private fun NoteScreenPreviewLight() {
    AnoteiTheme {
        NoteHeader(
            noteCategoryColor = AnoteiAppTheme.colors.allChipColor,
            titleInitialValue = "",
            creationDateValue = "30 de Outubro, 2024",
            onTitleChanged = {},
            isNewNote = false,
            titleMaxLength = "40",
            onTitleDone = {}
        )
    }
}