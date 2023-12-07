package br.com.ascence.anotei.ui.screens.note

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.ascence.anotei.navigation.NOTE_EXTRA
import br.com.ascence.anotei.navigation.activitycontracts.newnote.NoteType
import br.com.ascence.anotei.navigation.extensions.getEnumExtra
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class NoteActivity : ComponentActivity() {

    private val noteType by lazy {
        intent.getEnumExtra(key = NOTE_EXTRA, defaultValue = NoteType.NEW_NOTE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnoteiTheme {
                NoteScreenContent(
                    noteType = noteType
                ) { result ->
                    finishWithResult(result)
                }
            }
        }
    }

    private fun finishWithResult(result: String) {
        val intentWithResult = Intent().apply {
            putExtra(NOTE_EXTRA, result)
        }
        setResult(RESULT_OK, intentWithResult)
        finish()
    }
}