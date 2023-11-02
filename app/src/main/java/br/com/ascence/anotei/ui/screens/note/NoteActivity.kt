package br.com.ascence.anotei.ui.screens.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.ascence.anotei.ui.theme.AnoteiTheme

class NoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnoteiTheme {
                NoteScreenContent {
                    finish()
                }
            }
        }
    }
}