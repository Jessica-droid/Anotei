package br.com.ascence.anotei.extension

import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.model.NoteStatus
import br.com.ascence.anotei.model.NoteStatusPresentation

fun Note.getStatusPresentation(): List<NoteStatusPresentation> =
    status.map { noteStatus ->
        when (noteStatus) {
            NoteStatus.PROTECTED -> NoteStatusPresentation.PROTECTED
            NoteStatus.SCHEDULED -> NoteStatusPresentation.SCHEDULED
        }
    }