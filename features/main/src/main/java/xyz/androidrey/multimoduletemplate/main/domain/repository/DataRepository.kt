package xyz.androidrey.multimoduletemplate.main.domain.repository

import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.network.NetworkResult

interface DataRepository {
    suspend fun getProducts(lastUserId: Int): NetworkResult<List<Product>>
}