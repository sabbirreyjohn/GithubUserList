package xyz.androidrey.multimoduletemplate.main.ui.home

import xyz.androidrey.multimoduletemplate.main.data.entity.Product

sealed class HomeUiState {
    data class ProductListLoaded(val users: List<Product>) : HomeUiState()

    data object ProductListLoading : HomeUiState()

    data class ProductListLoadingFailed(val message: String) : HomeUiState()
}