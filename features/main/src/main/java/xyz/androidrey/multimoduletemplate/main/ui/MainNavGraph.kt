package xyz.androidrey.multimoduletemplate.main.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import xyz.androidrey.multimoduletemplate.main.ui.home.HomeScreen
import xyz.androidrey.multimoduletemplate.main.ui.profile.ProfileScreen
import xyz.androidrey.multimoduletemplate.main.ui.profile.ProfileViewModel


const val mainRoute = "main"

sealed class MainScreen(val route: String) {
    data object Home : MainScreen("$mainRoute/home")
    data object Profile : MainScreen("$mainRoute/profile")
}

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = MainScreen.Home.route, route = mainRoute) {
        composable(MainScreen.Home.route) {
            HomeScreen(hiltViewModel(), navController)
        }
        composable("${MainScreen.Profile.route}?name={name}", arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
                defaultValue = "Sabbir"
            }
        )) {
            val viewModel = hiltViewModel<ProfileViewModel, ProfileViewModel.Factory>(
                creationCallback = { factory -> factory.create(it.arguments?.getString("name")!!) }
            )
            ProfileScreen(it.arguments?.getString("name"), viewModel)
        }
    }
}