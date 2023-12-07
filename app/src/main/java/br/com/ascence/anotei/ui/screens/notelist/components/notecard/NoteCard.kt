package br.com.ascence.anotei.ui.screens.notelist.components.notecard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.data.preview.mock.fakeTextNote
import br.com.ascence.anotei.model.extension.toStatusPresentation
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

private const val UNSELECTED_CARD_CONTENT_MAX_LINES = 3

@Composable
fun NoteCard(
    note: Note,
    isCardSelected: Boolean,
    onCardClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {

    val statusPresentation = remember { note.toStatusPresentation() }

    CardContent(
        note = note,
        isCardSelected = isCardSelected,
        statusPresentation = statusPresentation,
        onCardClick = onCardClick,
        modifier = modifier
    )
}

@Composable
private fun CardContent(
    note: Note,
    isCardSelected: Boolean,
    statusPresentation: List<NoteStatusPresentation>,
    onCardClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentMaxLines =
        if (isCardSelected) Int.MAX_VALUE else UNSELECTED_CARD_CONTENT_MAX_LINES
    val cardBackgroundColor = if (isCardSelected) AnoteiAppTheme.colors.selectedNoteColor
    else AnoteiAppTheme.colors.secondaryBackgroundColor

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor,
        ),
        modifier = modifier
            .animateContentSize()
            .selectable(
                selected = isCardSelected,
                onClick = {
                    onCardClick(note)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AnoteiAppTheme.spaces.medium)
        ) {
            NoteCardHeader(
                title = note.title,
                creationDate = note.creationDate.toString(), // TODO format date
                categoryColor = note.category.getColor(),
                status = statusPresentation
            )

            if (note is Note.TextNote) {
                Text(
                    text = note.description,
                    color = AnoteiAppTheme.colors.secondaryTextColor,
                    fontSize = AnoteiAppTheme.fontSizes.medium,
                    maxLines = contentMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = AnoteiAppTheme.spaces.xSmall)
                )
            }
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NoteCardPreview() {
    AnoteiTheme {
        CardContent(
            note = fakeTextNote,
            isCardSelected = false,
            statusPresentation = listOf(
                NoteStatusPresentation.SCHEDULED,
                NoteStatusPresentation.PROTECTED
            ),
            onCardClick = {}
        )
    }
}