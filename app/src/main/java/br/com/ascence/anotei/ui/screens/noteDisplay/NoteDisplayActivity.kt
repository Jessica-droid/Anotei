package br.com.ascence.anotei.ui.screens.noteDisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.navigation.NOTE_EXTRA
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class NoteDisplayActivity : ComponentActivity() {

    private val note by lazy {
        intent.getParcelableExtra<Note.TextNote>(NOTE_EXTRA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnoteiTheme {
//                NoteDisplay(
//                    note = note,
//                    onFinish = { finish() }
//                )
            }
        }
    }
}