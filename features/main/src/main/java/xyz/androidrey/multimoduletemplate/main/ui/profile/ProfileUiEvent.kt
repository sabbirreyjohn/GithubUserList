package xyz.androidrey.multimoduletemplate.main.ui.profile

sealed class ProfileUiEvent {
    data class LoadProfile(val name: String) : ProfileUiEvent()
}