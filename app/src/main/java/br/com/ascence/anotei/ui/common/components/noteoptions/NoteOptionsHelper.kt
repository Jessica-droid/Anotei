package br.com.ascence.anotei.ui.common.components.noteoptions

import androidx.compose.runtime.Composable
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteOption
import br.com.ascence.anotei.model.extension.getColor
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType

class NoteOptionsHelper {

    @Composable
    fun getOptions(
        noteType: NoteType,
        note: Note?,
        onCategoryClick: () -> Unit,
        onScheduleClick: () -> Unit,
        onProtectClick: () -> Unit,
        onDeleteClick: () -> Unit,
    ): List<NoteOption> {

        val categoryColor = getCategoryColor(noteType = noteType, note = note)
        val optionsList = mutableListOf(
            NoteOption.Category(
                action = onCategoryClick,
                iconColor = { categoryColor }
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

    @Composable
    private fun getCategoryColor(noteType: NoteType, note: Note?) =
        when {
            noteType == NoteType.NEW_NOTE -> Category.DEFAULT.getColor()
            noteType == NoteType.UPDATE_NOTE && note != null -> note.category.getColor()
            else -> Category.DEFAULT.getColor()
        }
}