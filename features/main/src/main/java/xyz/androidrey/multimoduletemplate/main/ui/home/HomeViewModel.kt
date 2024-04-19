package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.ProfileNotSelected)
    val uiState = _uiState.asStateFlow()

    fun onEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            HomeUiEvent.ProfileSelected -> {
                _uiState.value = HomeUiState.ProfileSelected
            }
        }
    }
}