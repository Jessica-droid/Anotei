package br.com.ascence.anotei.ui.screens.dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Text("Dashboard")
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    AnoteiTheme {
        Dashboard()
    }
}