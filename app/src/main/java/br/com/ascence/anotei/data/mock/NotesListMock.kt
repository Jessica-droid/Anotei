package br.com.ascence.anotei.data.mock

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus

val notesListMock = listOf(
    Note(
        id = "a",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    Note(
        id = "b",
        title = "Anotação Curta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.PROTECTED),
    ),
    Note(
        id = "c",
        title = "Anotação Média",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        id = "d",
        title = "Anotação Curtinha",
        description = "Lorem ipsum dolor sit amet.",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        id = "e",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.PROTECTED),
    ),
    Note(
        id = "f",
        title = "Anotação Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.  ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED),
    ),
    Note(
        id = "g",
        title = "Anotação Muito Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    Note(
        id = "h",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        id = "i",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        id = "j",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
)