package br.com.ascence.anotei.data.mock

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note.SimpleText
import br.com.ascence.anotei.model.NoteStatus
import java.util.Date

val notesListMock = listOf(
    SimpleText(
        id = 0,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        creationDate = Date(),
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    SimpleText(
        id = 1,
        title = "Anotação Curta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        creationDate = Date(),
        category = Category.RED,
        status = listOf(NoteStatus.PROTECTED),
    ),
    SimpleText(
        id = 2,
        title = "Anotação Média",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
        creationDate = Date(),
        category = Category.PURPLE,
        status = listOf(),
    ),
    SimpleText(
        id = 3,
        title = "Anotação Curtinha",
        description = "Lorem ipsum dolor sit amet.",
        creationDate = Date(),
        category = Category.GREEN,
        status = listOf(),
    ),
    SimpleText(
        id = 4,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = Date(),
        category = Category.PINK,
        status = listOf(NoteStatus.PROTECTED),
    ),
    SimpleText(
        id = 5,
        title = "Anotação Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.  ",
        creationDate = Date(),
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED),
    ),
    SimpleText(
        id = 6,
        title = "Anotação Muito Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = Date(),
        category = Category.RED,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    SimpleText(
        id = 7,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = Date(),
        category = Category.DEFAULT,
        status = listOf(),
    ),
    SimpleText(
        id = 8,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = Date(),
        category = Category.GREEN,
        status = listOf(),
    ),
    SimpleText(
        id = 9,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = Date(),
        category = Category.DEFAULT,
        status = listOf(),
    ),
)