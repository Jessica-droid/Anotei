package br.com.ascence.anotei.data.mock

import br.com.ascence.anotei.model.Category
import br.com.ascence.anotei.model.Note.TextNote
import br.com.ascence.anotei.model.NoteStatus
import java.util.Date

private val currentDate = Date()

val notesListMock = listOf(
    TextNote(
        id = 1,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        creationDate = currentDate,
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    TextNote(
        id = 2,
        title = "Anotação Curta",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        creationDate = currentDate,
        category = Category.RED,
        status = listOf(NoteStatus.PROTECTED),
    ),
    TextNote(
        id = 3,
        title = "Anotação Média",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
        creationDate = currentDate,
        category = Category.PURPLE,
        status = listOf(),
    ),
    TextNote(
        id = 4,
        title = "Anotação Curtinha",
        description = "Lorem ipsum dolor sit amet.",
        creationDate = currentDate,
        category = Category.GREEN,
        status = listOf(),
    ),
    TextNote(
        id = 5,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = currentDate,
        category = Category.PINK,
        status = listOf(NoteStatus.PROTECTED),
    ),
    TextNote(
        id = 6,
        title = "Anotação Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.  ",
        creationDate = currentDate,
        category = Category.DEFAULT,
        status = listOf(NoteStatus.SCHEDULED),
    ),
    TextNote(
        id = 7,
        title = "Anotação Muito Longa",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = currentDate,
        category = Category.RED,
        status = listOf(NoteStatus.SCHEDULED, NoteStatus.PROTECTED),
    ),
    TextNote(
        id = 8,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = currentDate,
        category = Category.DEFAULT,
        status = listOf(),
    ),
    TextNote(
        id = 9,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = currentDate,
        category = Category.GREEN,
        status = listOf(),
    ),
    TextNote(
        id = 10,
        title = "Anotação",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
        creationDate = currentDate,
        category = Category.DEFAULT,
        status = listOf(),
    ),
)