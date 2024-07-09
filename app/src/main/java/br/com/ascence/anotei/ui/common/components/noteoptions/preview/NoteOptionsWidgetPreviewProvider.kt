package br.com.ascence.anotei.ui.common.components.noteoptions.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.ui.common.components.noteoptions.presentation.NoteOptionsMode
import java.util.Date

internal data class NoteOptionsPreviewParams(
    val mode: NoteOptionsMode,
    val selectedNotesCount: List<Note>,
    val selectionModeActivated: Boolean,
)


internal val fakeSelectedNote = listOf(
    Note.TextNote(
        id = 0,
        title = "Nota Fake",
        status = emptyList(),
        category = Category.DEFAULT,
        creationDate = Date(),
        description = ""
    )
)

internal val fakeSelectedNotesList = listOf(
    Note.TextNote(
        id = 0,
        title = "Nota Fake",
        status = emptyList(),
        category = Category.RED,
        creationDate = Date(),
        description = ""
    ),
    Note.TextNote(
        id = 0,
        title = "Nota Fake",
        status = emptyList(),
        category = Category.YELLOW,
        creationDate = Date(),
        description = ""
    ),
    Note.TextNote(
        id = 0,
        title = "Nota Fake",
        status = emptyList(),
        category = Category.PURPLE,
        creationDate = Date(),
        description = ""
    ),
)

internal class NoteOptionsWidgetPreviewProvider : PreviewParameterProvider<NoteOptionsPreviewParams> {

    override val values: Sequence<NoteOptionsPreviewParams>
        get() = sequenceOf(
            NoteOptionsPreviewParams(
                mode = NoteOptionsMode.CREATE_MODE,
                selectedNotesCount = fakeSelectedNote,
                selectionModeActivated = false
            ),
            NoteOptionsPreviewParams(
                mode = NoteOptionsMode.PREVIEW_MODE,
                selectedNotesCount = fakeSelectedNote,
                selectionModeActivated = false
            ),
            NoteOptionsPreviewParams(
                mode = NoteOptionsMode.EDIT_MODE,
                selectedNotesCount = fakeSelectedNote,
                selectionModeActivated = false
            ),
            NoteOptionsPreviewParams(
                mode = NoteOptionsMode.PREVIEW_MODE,
                selectedNotesCount = fakeSelectedNotesList,
                selectionModeActivated = true
            ),
        )
}