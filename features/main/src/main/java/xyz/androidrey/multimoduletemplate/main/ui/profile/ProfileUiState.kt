package xyz.androidrey.multimoduletemplate.main.ui.profile

import xyz.androidrey.multimoduletemplate.main.data.entity.Profile

sealed class ProfileUiState {
    data class ProfileLoaded(val profile: Profile) : ProfileUiState()

    data object ProfileLoading : ProfileUiState()

    data class ProfileLoadingFailed(val message: String) : ProfileUiState()
}