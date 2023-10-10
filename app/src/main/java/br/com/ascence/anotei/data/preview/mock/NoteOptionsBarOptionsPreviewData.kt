package br.com.ascence.anotei.data.preview.mock

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import br.com.ascence.anotei.model.NoteOption

val noteOptionsPreview = listOf(
    NoteOption(
        icon = Icons.Default.Brightness1,
        contentDescription = "",
        action = {}
    ),
    NoteOption(
        icon = Icons.Default.Schedule,
        contentDescription = "",
        action = {}
    ),
    NoteOption(
        icon = Icons.Default.Lock,
        contentDescription = "",
        action = {}
    ),
    NoteOption(
        icon = Icons.Default.Delete,
        contentDescription = "",
        action = {}
    ),
)