package xyz.androidrey.multimoduletemplate.main.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.domain.util.Status
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext private val context: Context, private val repo: DataRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.ProfileListLoading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when(val status = repo.getUsersFromServer(0)){
                is Status.Error -> _uiState.value = HomeUiState.ProfileListLoadingFailed(status.message!!)
                is Status.Loading -> _uiState.value = HomeUiState.ProfileListLoading
                is Status.Success -> _uiState.value = HomeUiState.ProfileListLoaded(status.data!!)

            }
        }
    }

    fun onEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {

            HomeUiEvent.ProfileClicked -> {}
        }
    }
}