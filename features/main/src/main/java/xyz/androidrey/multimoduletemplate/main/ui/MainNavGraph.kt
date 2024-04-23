package xyz.androidrey.multimoduletemplate.main.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import xyz.androidrey.multimoduletemplate.main.ui.home.HomeScreen


const val mainRoute = "main"

sealed class MainScreen(val route: String) {
    data object Home : MainScreen("$mainRoute/home")
    data object Profile : MainScreen("$mainRoute/profile")
}

fun NavGraphBuilder.mainNavGraph(navController: NavController) {

    navigation(startDestination = MainScreen.Home.route, route = mainRoute){
        composable(MainScreen.Home.route){
            HomeScreen(hiltViewModel())
        }
        composable(MainScreen.Profile.route){

        }
    }
}