package br.com.ascence.anotei.navigation.activitycontracts.newnote

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import br.com.ascence.anotei.model.Note
import br.com.ascence.anotei.navigation.NOTE_EXTRA
import br.com.ascence.anotei.navigation.NOTE_TYPE_EXTRA
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.navigation.extensions.putEnumExtra
import br.com.ascence.anotei.ui.screens.note.NoteActivity

class NewNoteActivityResultContract(val note: Note.TextNote? = null) :
    ActivityResultContract<NoteType, String?>() {

    override fun createIntent(context: Context, input: NoteType): Intent {
        return Intent(
            context,
            NoteActivity::class.java
        ).apply {
            putEnumExtra(NOTE_TYPE_EXTRA, input)
            putExtra(NOTE_EXTRA, note)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(
                NOTE_TYPE_EXTRA
            )
        } else {
            NOTE_RESULT_NOTHING
        }
    }
}