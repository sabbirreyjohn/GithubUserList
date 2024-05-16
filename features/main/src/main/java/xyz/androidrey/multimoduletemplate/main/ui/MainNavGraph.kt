package xyz.androidrey.multimoduletemplate.main.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import xyz.androidrey.multimoduletemplate.main.ui.home.HomeScreen
import xyz.androidrey.multimoduletemplate.main.ui.profile.ProfileScreen
import xyz.androidrey.multimoduletemplate.main.ui.profile.ProfileViewModel

@Serializable
sealed class MainScreen() {
    @Serializable
    data object Home : MainScreen()

    @Serializable
    data class Profile(val name: String) : MainScreen()
}

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreen.Home) {
        composable<MainScreen.Home> {
            HomeScreen(hiltViewModel(), navController)
        }
        composable<MainScreen.Profile> {
            val args = it.toRoute<MainScreen.Profile>()
            val viewModel = hiltViewModel<ProfileViewModel, ProfileViewModel.Factory>(
                creationCallback = { factory -> factory.create(args.name) }
            )
            ProfileScreen(args.name, viewModel)
        }
    }
}