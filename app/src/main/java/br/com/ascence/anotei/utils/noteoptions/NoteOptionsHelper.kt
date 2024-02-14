package br.com.ascence.anotei.utils.noteoptions

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType

class NoteOptionsHelper {

    fun getOptions(
        noteType: NoteType,
        noteCategory: Category,
        onCategoryClick: () -> Unit,
        onScheduleClick: () -> Unit,
        onProtectClick: () -> Unit,
        onDeleteClick: () -> Unit,
    ): List<NoteOption> {

        val optionsList = mutableListOf(
            NoteOption.Category(
                action = onCategoryClick,
                iconColor = { noteCategory.getColor() }
            ),
            NoteOption.Schedule(
                action = onScheduleClick
            ),
            NoteOption.Protect(
                action = onProtectClick
            ),
        )

        if (noteType == NoteType.UPDATE_NOTE) {
            optionsList.add(
                NoteOption.Delete(
                    action = onDeleteClick
                )
            )
        }

        return optionsList.toList()
    }
}