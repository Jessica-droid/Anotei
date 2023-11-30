package br.com.ascence.anotei.navigation.activitycontracts.newnote

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import br.com.ascence.anotei.navigation.NEW_NOTE_EXTRA
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import br.com.ascence.anotei.ui.screens.note.NoteActivity

class NewNoteActivityResultContract : ActivityResultContract<NoteType, String?>() {

    override fun createIntent(context: Context, input: NoteType): Intent {
        return Intent(
            context,
            NoteActivity::class.java
        ).apply {
            putExtra(NEW_NOTE_EXTRA, input)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(
                NEW_NOTE_EXTRA
            )
        } else {
            NOTE_RESULT_NOTHING
        }
    }
}