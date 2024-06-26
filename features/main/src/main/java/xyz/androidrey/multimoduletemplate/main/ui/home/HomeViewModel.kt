package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.domain.entity.Product
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pager: Pager<Int, Product>,
    private val repo: DataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.ProfileListLoading)
    val uiState = _uiState.asStateFlow()

//    val lazyPagingItems: Flow<PagingData<User>> = Pager(
//        config = PagingConfig(pageSize = 10),
//        pagingSourceFactory = { ThePagingSource(repo) }
//    ).flow.cachedIn(viewModelScope)


    val lazyPagingMediatorItem = pager.flow.cachedIn(viewModelScope)

//    init {
//        viewModelScope.launch {
//            when (val status = repo.getUsers(0)) {
//                is NetworkResult.Error -> _uiState.value =
//                    HomeUiState.ProfileListLoadingFailed(status.exception.message!!)
//
//                is NetworkResult.Success -> _uiState.value =
//                    HomeUiState.ProfileListLoaded(status.result)
//            }
//        }
//    }
}