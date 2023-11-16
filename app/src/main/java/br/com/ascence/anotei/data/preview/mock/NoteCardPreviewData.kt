package br.com.ascence.anotei.data.preview.mock

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note.TextNote
import br.com.ascence.anotei.model.NoteStatus
import java.util.Date

val fakeTextNote = TextNote(
    id = 1,
    title = "Titulo da nota",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
    creationDate = Date(),
    category = Category.DEFAULT,
    status = listOf(
        NoteStatus.SCHEDULED,
        NoteStatus.PROTECTED
    )
)