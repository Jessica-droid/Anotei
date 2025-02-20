package br.com.ascence.anotei.model

enum class AlertType(
    val title: String,
    val message: String,
    val confirmButtonLabel: String,
    val dismissButtonLabel: String? = null,
) {
    CONFIRM_NOTE_DISCARD(
        title = "Descartar nota?",
        message = "Deseja descartar o que anotou até o momento?",
        confirmButtonLabel = "Confirmar",
        dismissButtonLabel = "Cancelar"
    ),
    EMPTY_NOTE_CONTENT(
        title = "Vai com calma",
        message = "Anote alguma coisa antes de salvar.",
        confirmButtonLabel = "Entendi"
    ),
    DELETE_NOTE_CONFIRMATION(
        title = "Apagar nota",
        message = "Deseja realmente apagar esta nota?",
        confirmButtonLabel = "Confirmar",
        dismissButtonLabel = "Cancelar",
    ),
}