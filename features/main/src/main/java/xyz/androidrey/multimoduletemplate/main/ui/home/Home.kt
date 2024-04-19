package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import xyz.androidrey.multimoduletemplate.theme.components.AppBar
import xyz.androidrey.multimoduletemplate.theme.components.ThePreview

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        HomeUiState.ProfileNotSelected -> {
            Home()
        }

        HomeUiState.ProfileSelected -> {

        }
    }

}

@Composable
fun Home() {
    AppBar("Home") {

    }
}

@ThePreview
@Composable
fun PreviewHome() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Home()
        }
    }
}