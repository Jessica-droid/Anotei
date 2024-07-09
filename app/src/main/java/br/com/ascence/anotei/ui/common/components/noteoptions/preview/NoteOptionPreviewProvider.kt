package br.com.ascence.anotei.ui.common.components.noteoptions.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.ascence.anotei.model.NoteOption

internal data class NoteOptionPreviewParams(
    val option: NoteOption,
    val showBadge: Boolean,
)

internal class NoteOptionPreviewProvider : PreviewParameterProvider<NoteOptionPreviewParams> {

    override val values: Sequence<NoteOptionPreviewParams>
        get() = sequenceOf(
            NoteOptionPreviewParams(
                option = NoteOption.Category(),
                showBadge = false
            ),
            NoteOptionPreviewParams(
                option = NoteOption.Schedule(),
                showBadge = false
            ),
            NoteOptionPreviewParams(
                option = NoteOption.Protect(),
                showBadge = false
            ),
            NoteOptionPreviewParams(
                option = NoteOption.Delete(),
                showBadge = false
            ),

            NoteOptionPreviewParams(
                option = NoteOption.Schedule(),
                showBadge = true
            ),
            NoteOptionPreviewParams(
                option = NoteOption.Protect(),
                showBadge = true
            ),
        )
}