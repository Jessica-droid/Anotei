package br.com.ascence.anotei.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.ui.screens.notes.NotesEmptyState
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        NotesEmptyState(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = AnoteiAppTheme.colors.colorScheme.background)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() =
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

@Composable
private fun FAB() =
    FloatingActionButton(
        containerColor = AnoteiAppTheme.colors.colorScheme.primary,
        onClick = { } // TODO setup onClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Criar anotação", // TODO replace this type of string
            tint = AnoteiAppTheme.colors.primaryButtonTextColor
        )
    }

@Preview(showBackground = true)
@Composable
fun DashboardPreviewLight() {
    AnoteiTheme(darkTheme = false) {
        Dashboard()
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreviewDark() {
    AnoteiTheme(darkTheme = true) {
        Dashboard()
    }
}