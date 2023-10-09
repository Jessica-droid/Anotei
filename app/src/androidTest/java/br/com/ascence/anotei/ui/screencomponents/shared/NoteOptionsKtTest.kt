package br.com.ascence.anotei.ui.screencomponents.shared

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.ascence.anotei.model.NoteOptionsPresentationType
import br.com.ascence.anotei.ui.theme.AnoteiTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteOptionsKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private fun setup(
        optionType: NoteOptionsPresentationType,
    ) {
        composeTestRule.setContent {
            AnoteiTheme {
                OptionMainAction(
                    optionType = optionType,
                    onFABClick = {}
                )
            }
        }
    }

    @Test
    fun verifyConfirmationIconAccordingToPresentationType() {
        setup(NoteOptionsPresentationType.EDIT_MODE)
        composeTestRule.onNodeWithTag(FAB_ICON_TEST_TAG, useUnmergedTree = true)
            .assertContentDescriptionContains("Confirmar alterações")
    }

    @Test
    fun verifyEditIconAccordingToPresentationType() {
        setup(NoteOptionsPresentationType.PREVIEW_MODE)
        composeTestRule.onNodeWithTag(FAB_ICON_TEST_TAG, useUnmergedTree = true)
            .assertContentDescriptionContains("Alterar nota")
    }
}