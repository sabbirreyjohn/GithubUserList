package xyz.androidrey.multimoduletemplate.main.ui.home

sealed class HomeUiEvent {
    data object ProfileSelected: HomeUiEvent()
}