package br.com.ascence.anotei.ui.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

abstract class FeatureScreen {

    open val screenTitle: String = ""
    open val onBackPressed: (() -> Unit)? = null

    @Composable
    abstract fun CustomContent(
        navBackStackEntry: NavBackStackEntry,
        navController: NavController,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScreenContent(
        navBackStackEntry: NavBackStackEntry,
        navController: NavController,
    ) {

        BackHandler(
            enabled = onBackPressed != null,
            onBack = { onBackPressed?.invoke() }
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = screenTitle) })
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                CustomContent(
                    navBackStackEntry = navBackStackEntry,
                    navController = navController
                )
            }
        }
    }
}