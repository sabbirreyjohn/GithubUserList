package xyz.androidrey.multimoduletemplate.main.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import xyz.androidrey.multimoduletemplate.theme.components.AppBar
import javax.inject.Inject

@Composable
fun ProfileScreen(name: String? = "Sabbir", viewModel: ProfileViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(name!!)
        ProfileStateHandler(state = uiState) {
            Profile(it.name!!)
        }
    }
}

@Composable
fun Profile(name: String) {
    Text(text = name)
}