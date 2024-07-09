package br.com.ascence.anotei.data.preview.mock

import br.com.ascence.anotei.model.NoteOption.Category
import br.com.ascence.anotei.model.NoteOption.Protect
import br.com.ascence.anotei.model.NoteOption.Schedule
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

val noteOptionsPreview = listOf(
    Category(
        iconColor = { AnoteiAppTheme.colors.allChipColor }
    ),
    Schedule(),
    Protect(),
)