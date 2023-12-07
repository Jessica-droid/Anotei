package br.com.ascence.anotei.ui.screens.note

import androidx.lifecycle.ViewModel
import br.com.ascence.anotei.navigation.NOTE_RESULT_NOTHING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NoteScreenState())
    val screenState: StateFlow<NoteScreenState> = _uiState.asStateFlow()

    fun onTitleUpdate(title: String) {
        if (title.length <= TITLE_MAX_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(
                    title = title
                )
            }
        }
    }

    fun onDescriptionUpdate(description: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = description
            )
        }
    }

    fun hideContentAlertDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = false
            )
        }
    }

    fun handleOnBackPressed(onCloseScreen: (String) -> Unit) {
        _uiState.update { currentState ->
            currentState.copy(
                showContentAlert = currentState.description.isNotEmpty()
            )
        }
        if (_uiState.value.showContentAlert.not()) {
            onCloseScreen(NOTE_RESULT_NOTHING)
        }
    }

    private companion object {
        const val TITLE_MAX_LENGTH = 40
    }
}