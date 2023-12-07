package br.com.ascence.anotei.navigation.activitycontracts.newnote

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import br.com.ascence.anotei.navigation.NOTE_EXTRA
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.extensions.putEnumExtra
import br.com.ascence.anotei.ui.screens.note.NoteActivity

class NewNoteActivityResultContract : ActivityResultContract<NoteType, String?>() {

    override fun createIntent(context: Context, input: NoteType): Intent {
        return Intent(
            context,
            NoteActivity::class.java
        ).apply {
            putEnumExtra(NOTE_EXTRA, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(
                NOTE_EXTRA
            )
        } else {
            NOTE_RESULT_NOTHING
        }
    }
}