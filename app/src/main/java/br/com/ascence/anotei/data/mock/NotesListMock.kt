package br.com.ascence.anotei.data.mock

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note.TextNote
import br.com.ascence.anotei.model.NoteStatus

val notesListMock = listOf(
    TextNote(
        id = "a",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        creationDate = "27 de Setembro",
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    TextNote(
        id = "b",
        title = "Anotação Curta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        creationDate = "27 de Setembro",
        category = Category.RED,
        status = listOf(NoteStatus.PROTECTED),
    ),
    TextNote(
        id = "c",
        title = "Anotação Média",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
        creationDate = "27 de Setembro",
        category = Category.PURPLE,
        status = listOf(),
    ),
    TextNote(
        id = "d",
        title = "Anotação Curtinha",
        description = "Lorem ipsum dolor sit amet.",
        creationDate = "27 de Setembro",
        category = Category.GREEN,
        status = listOf(),
    ),
    TextNote(
        id = "e",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        category = Category.PINK,
        status = listOf(NoteStatus.PROTECTED),
    ),
    TextNote(
        id = "f",
        title = "Anotação Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.  ",
        creationDate = "27 de Setembro",
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED),
    ),
    TextNote(
        id = "g",
        title = "Anotação Muito Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        category = Category.RED,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    TextNote(
        id = "h",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        category = Category.DEFAULT,
        status = listOf(),
    ),
    TextNote(
        id = "i",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        category = Category.GREEN,
        status = listOf(),
    ),
    TextNote(
        id = "j",
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = "27 de Setembro",
        category = Category.DEFAULT,
        status = listOf(),
    ),
)