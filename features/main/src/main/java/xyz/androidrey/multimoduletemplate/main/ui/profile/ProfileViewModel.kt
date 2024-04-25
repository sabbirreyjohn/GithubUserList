package xyz.androidrey.multimoduletemplate.main.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepository
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
     private val repository: DataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.ProfileLoading)
    val uiState = _uiState.asStateFlow()

    fun onEvent(uiEvent: ProfileUiEvent) {
        when (uiEvent) {
            is ProfileUiEvent.LoadProfile -> {
                viewModelScope.launch {
                    when (val status = repository.getProfile(uiEvent.name)) {
                        is NetworkResult.Error -> _uiState.value =
                            ProfileUiState.ProfileLoadingFailed(status.exception.message!!)

                        is NetworkResult.Success -> _uiState.value =
                            ProfileUiState.ProfileLoaded(status.result)
                    }
                }
            }
        }

    }
}