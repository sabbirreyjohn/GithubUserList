package xyz.androidrey.multimoduletemplate.main.ui.profile

import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile

sealed class ProfileUiEvent {
    data class LoadProfile(val name: String) : ProfileUiEvent()
}