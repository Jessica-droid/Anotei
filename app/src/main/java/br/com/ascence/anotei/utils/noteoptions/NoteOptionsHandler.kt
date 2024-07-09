package br.com.ascence.anotei.utils.noteoptions

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.ui.common.components.noteoptions.presentation.NoteOptionsMode
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

internal class NoteOptionsHandler {

    fun getNoteOptions(
        mode: NoteOptionsMode,
        selectedNoteList: List<Note>,
    ): List<NoteOption> {

        val optionsList = mutableListOf<NoteOption>()

        val defaultOptions = listOf(
            NoteOption.Schedule(),
            NoteOption.Protect(),
        )

        optionsList.add(
            NoteOption.Category(
                iconColor = {
                    selectedNoteList.firstOrNull()?.category?.getColor()
                        ?: AnoteiAppTheme.colors.allChipColor
                }
            ),
        )

        optionsList.addAll(defaultOptions)

        if (mode != NoteOptionsMode.CREATE_MODE) {
            optionsList.add(
                NoteOption.Delete()
            )
        }

        return optionsList.toList()
    }
}