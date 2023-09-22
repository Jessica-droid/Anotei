package br.com.ascence.anotei.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ascence.anotei.ui.theme.AnoteiAppTheme
import br.com.ascence.anotei.ui.theme.AnoteiTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AnoteiAppTheme.colors.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Dashboard",
                fontSize = AnoteiAppTheme.fontSizes.medium,
                color = AnoteiAppTheme.colors.primaryTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    AnoteiTheme {
        Dashboard()
    }
}