package xyz.androidrey.multimoduletemplate.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import xyz.androidrey.multimoduletemplate.main.ui.mainNavGraph
import xyz.androidrey.multimoduletemplate.main.ui.mainRoute

@Composable
fun TheNavHost(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = mainRoute) {
        mainNavGraph(navController = navHostController)
    }

}