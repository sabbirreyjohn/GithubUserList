package xyz.androidrey.multimoduletemplate.main.ui.profile

import androidx.compose.runtime.Composable
import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowError
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowLoading

@Composable
fun  ProfileStateHandler(
    state: ProfileUiState,
    loading: @Composable () -> Unit = { ShowLoading() },
    error: @Composable (String) -> Unit = { ShowError(it) },
    content: @Composable (Profile) -> Unit,
) {
    when (state) {
        is ProfileUiState.ProfileLoaded -> content(state.profile)
        is ProfileUiState.ProfileLoading -> loading()
        is ProfileUiState.ProfileLoadingFailed -> error(state.message)
    }
}