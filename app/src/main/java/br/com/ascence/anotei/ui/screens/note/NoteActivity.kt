package br.com.ascence.anotei.ui.screens.note

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.ascence.anotei.navigation.NEW_NOTE_EXTRA
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class NoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnoteiTheme {
                NoteScreenContent { result ->
                    finishWithResult(result)
                }
            }
        }
    }

    private fun finishWithResult(result: String) {
        val intentWithResult = Intent().apply {
            putExtra(NEW_NOTE_EXTRA, result)
        }
        setResult(RESULT_OK, intentWithResult)
        finish()
    }
}