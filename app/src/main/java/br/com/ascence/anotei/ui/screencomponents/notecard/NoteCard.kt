package br.com.ascence.anotei.ui.screencomponents.notecard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.model.NoteStatusPresentation
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

private const val UNSELECTED_CARD_CONTENT_MAX_LINES = 3
private const val CARD_CONTENT_PREVIEW =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"

@Composable
fun NoteCard(
    title: String,
    creationDate: String,
    noteContent: String,
    noteColor: Color,
    noteStatus: List<NoteStatusPresentation>,
    onCardClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    val isCardSelected = remember { mutableStateOf(false) }

    val contentMaxLines =
        if (isCardSelected.value) Int.MAX_VALUE else UNSELECTED_CARD_CONTENT_MAX_LINES
    val cardBackgroundColor = if (isCardSelected.value) AnoteiAppTheme.colors.selectedNoteColor
    else AnoteiAppTheme.colors.secondaryBackgroundColor

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor,
        ),
        modifier = modifier.animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AnoteiAppTheme.spaces.medium)
                .clickable {
                    isCardSelected.value = !isCardSelected.value
                    onCardClick(isCardSelected.value)
                }
        ) {
            NoteCardHeader(
                title = title,
                creationDate = creationDate,
                categoryColor = noteColor,
                status = noteStatus
            )

            Text(
                text = noteContent,
                color = AnoteiAppTheme.colors.secondaryTextColor,
                fontSize = AnoteiAppTheme.fontSizes.small,
                maxLines = contentMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = AnoteiAppTheme.spaces.xSmall)
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreviewDark() {
    AnoteiTheme(darkTheme = false) {
        NoteCard(
            title = "Título",
            creationDate = "25 de Setembro",
            noteContent = CARD_CONTENT_PREVIEW,
            noteColor = AnoteiAppTheme.colors.allChipColor,
            noteStatus = listOf(NoteStatusPresentation.SCHEDULED, NoteStatusPresentation.PROTECTED),
            onCardClick = {}
        )
    }
}

@Preview
@Composable
private fun NoteCardPreviewLight() {
    AnoteiTheme(darkTheme = true) {
        NoteCard(
            title = "Título",
            creationDate = "25 de Setembro",
            noteContent = CARD_CONTENT_PREVIEW,
            noteColor = AnoteiAppTheme.colors.allChipColor,
            noteStatus = listOf(NoteStatusPresentation.SCHEDULED, NoteStatusPresentation.PROTECTED),
            onCardClick = {}
        )
    }
}