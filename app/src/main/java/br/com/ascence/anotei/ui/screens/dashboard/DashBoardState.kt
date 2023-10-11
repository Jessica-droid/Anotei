package br.com.ascence.anotei.ui.screens.dashboard

import br.com.ascence.anotei.model.NoteOption

data class DashBoardState(
    val noteOptions: List<NoteOption> = emptyList()
)