package br.com.ascence.anotei.ui.screens.dashboard.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Anotei", // TODO replace this type of string
                color = AnoteiAppTheme.colors.appTitleColor,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = AnoteiAppTheme.colors.colorScheme.background
        )
    )
}