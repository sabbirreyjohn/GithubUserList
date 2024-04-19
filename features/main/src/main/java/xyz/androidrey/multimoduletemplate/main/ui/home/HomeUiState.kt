package xyz.androidrey.multimoduletemplate.main.ui.home

sealed class HomeUiState {
    data object ProfileSelected : HomeUiState()
    data object ProfileNotSelected: HomeUiState()
}