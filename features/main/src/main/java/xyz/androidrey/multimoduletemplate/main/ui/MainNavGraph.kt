package xyz.androidrey.multimoduletemplate.main.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import xyz.androidrey.multimoduletemplate.main.ui.home.HomeScreen

@Serializable
sealed class MainScreen() {
    @Serializable
    data object Home : MainScreen()
}

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreen.Home) {
        composable<MainScreen.Home> {
            HomeScreen(hiltViewModel(), navController)
        }
    }
}