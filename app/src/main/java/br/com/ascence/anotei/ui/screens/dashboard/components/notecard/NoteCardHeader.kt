package br.com.ascence.anotei.ui.screens.dashboard.components.notecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import br.com.ascence.anotei.data.preview.ColorSchemePreviews
import br.com.ascence.anotei.ui.presentation.NoteStatusPresentation
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

private const val HEADER_TITLE_MAX_LINES = 1

@Composable
fun NoteCardHeader(
    title: String,
    creationDate: String,
    categoryColor: Color,
    status: List<NoteStatusPresentation>,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TitleAndCategoryColor(title = title, color = categoryColor)
            Text(
                text = creationDate,
                color = AnoteiAppTheme.colors.tertiaryTextColor,
                fontSize = AnoteiAppTheme.fontSizes.small,
                fontWeight = FontWeight.SemiBold
            )
        }
        NoteStatus(noteStatus = status)
    }
}

@Composable
private fun TitleAndCategoryColor(title: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(AnoteiAppTheme.spaces.medium)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = title,
            fontSize = AnoteiAppTheme.fontSizes.medium,
            color = AnoteiAppTheme.colors.primaryTextColor,
            fontWeight = FontWeight.SemiBold,
            maxLines = HEADER_TITLE_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = AnoteiAppTheme.spaces.xSmall),
        )
    }
}

@Composable
private fun NoteStatus(noteStatus: List<NoteStatusPresentation>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = AnoteiAppTheme.spaces.xSmall)
    ) {
        noteStatus.map { status ->
            Icon(
                imageVector = status.icon,
                tint = status.iconColor(),
                contentDescription = status.contentDescription,
                modifier = Modifier.size(AnoteiAppTheme.spaces.large)
            )
        }
    }
}

@ColorSchemePreviews
@Composable
private fun NoteCardHeaderPreviewLight() {
    AnoteiTheme {
        NoteCardHeader(
            title = "Título",
            creationDate = "25 de Setembro",
            categoryColor = AnoteiAppTheme.colors.allChipColor,
            status = listOf(NoteStatusPresentation.SCHEDULED, NoteStatusPresentation.PROTECTED),
            modifier = Modifier
                .fillMaxWidth()
                .background(AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}