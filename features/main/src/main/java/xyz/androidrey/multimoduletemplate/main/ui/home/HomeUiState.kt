package xyz.androidrey.multimoduletemplate.main.ui.home

import xyz.androidrey.multimoduletemplate.main.data.entity.User

sealed class HomeUiState {
    data class ProfileListLoaded(val users: List<User>) : HomeUiState()

    data object ProfileListLoading : HomeUiState()

    data class ProfileListLoadingFailed(val message: String) : HomeUiState()
}