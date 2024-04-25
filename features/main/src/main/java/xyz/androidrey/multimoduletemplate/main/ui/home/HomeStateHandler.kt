package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.runtime.Composable
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowError
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowLoading

@Composable
fun  HomeStateHandler(
    state: HomeUiState,
    loading: @Composable () -> Unit = { ShowLoading() },
    error: @Composable (String) -> Unit = { ShowError(it) },
    content: @Composable (List<User>) -> Unit,
) {
    when (state) {
        is HomeUiState.ProfileListLoaded -> content(state.users)
        is HomeUiState.ProfileListLoading -> loading()
        is HomeUiState.ProfileListLoadingFailed -> error(state.message)
    }
}