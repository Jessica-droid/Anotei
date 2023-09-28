package br.com.ascence.anotei.data.mock

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus

val notesListMock = listOf(
    Note(
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    Note(
        title = "Anotação Curta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.PROTECTED),
    ),
    Note(
        title = "Anotação Média",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        title = "Anotação Curtinha",
        description = "Lorem ipsum dolor sit amet.",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.PROTECTED),
    ),
    Note(
        title = "Anotação Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.  ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED),
    ),
    Note(
        title = "Anotação Muito Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    Note(
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
    Note(
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        status = listOf(),
    ),
)