package xyz.androidrey.multimoduletemplate.main.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepository
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repo: DataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.ProfileListLoading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val status = repo.getUsers(0)) {
                is NetworkResult.Error -> _uiState.value =
                    HomeUiState.ProfileListLoadingFailed(status.exception.message!!)

                is NetworkResult.Success -> _uiState.value =
                    HomeUiState.ProfileListLoaded(status.result)
            }
        }
    }
}