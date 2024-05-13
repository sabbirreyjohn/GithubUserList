package xyz.androidrey.multimoduletemplate.main.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepository
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import javax.inject.Inject

@HiltViewModel(assistedFactory = ProfileViewModel.Factory::class)
class ProfileViewModel @AssistedInject constructor(
    private val repository: DataRepository,
    @Assisted private val name: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.ProfileLoading)
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(ProfileUiEvent.LoadProfile(name))
    }

    fun onEvent(uiEvent: ProfileUiEvent) {
        when (uiEvent) {
            is ProfileUiEvent.LoadProfile -> {
                viewModelScope.launch {
                    when (val status = repository.getProfile(uiEvent.name)) {
                        is NetworkResult.Error -> _uiState.value =
                            ProfileUiState.ProfileLoadingFailed("${status.exception.message!!} and ${uiEvent.name}")

                        is NetworkResult.Success -> _uiState.value =
                            ProfileUiState.ProfileLoaded(status.result)
                    }
                }
            }
        }

    }

    @AssistedFactory
    interface Factory {
        fun create(name: String): ProfileViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            name: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(name) as T
            }
        }

    }
}