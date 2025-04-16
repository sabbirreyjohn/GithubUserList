package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.runtime.Composable
import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowError
import xyz.androidrey.multimoduletemplate.main.ui.composable.ShowLoading

@Composable
fun  HomeStateHandler(
    state: HomeUiState,
    loading: @Composable () -> Unit = { ShowLoading() },
    error: @Composable (String) -> Unit = { ShowError(it) },
    content: @Composable (List<Product>) -> Unit,
) {
    when (state) {
        is HomeUiState.ProductListLoaded -> content(state.users)
        is HomeUiState.ProductListLoading -> loading()
        is HomeUiState.ProductListLoadingFailed -> error(state.message)
    }
}