package br.com.ascence.anotei.ui.screens.note.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Criar anotação", // TODO replace this type of string
                tint = AnoteiAppTheme.colors.colorScheme.primary,
                modifier = Modifier.clickable {
                    onBackPressed()
                }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = AnoteiAppTheme.colors.colorScheme.background
        )
    )
}