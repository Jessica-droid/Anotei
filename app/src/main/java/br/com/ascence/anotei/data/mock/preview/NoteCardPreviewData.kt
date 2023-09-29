package br.com.ascence.anotei.data.mock.preview

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus

val fakeNote = Note(
    id = "",
    title = "Titulo da nota",
    description =  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
    creationDate = "28 de Setembro",
    status = listOf(
        NoteStatus.SCHEDULED,
        NoteStatus.PROTECTED
    )
)