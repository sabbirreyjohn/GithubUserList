package xyz.androidrey.multimoduletemplate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import xyz.androidrey.multimoduletemplate.main.mainNavGraph
import xyz.androidrey.multimoduletemplate.main.mainRoute

@Composable
fun TheNavHost(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = mainRoute) {
        mainNavGraph(navController = navHostController)
    }

}