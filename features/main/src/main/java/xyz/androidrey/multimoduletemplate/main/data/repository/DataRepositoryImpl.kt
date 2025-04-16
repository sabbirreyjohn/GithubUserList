package xyz.androidrey.multimoduletemplate.main.data.repository

import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepository
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import javax.inject.Inject

class DataRepositoryImpl @Inject internal constructor(private val requestHandler: RequestHandler) :
    DataRepository {
    override suspend fun getProducts(lastUserId: Int) = requestHandler.get<List<Product>>(
        urlPathSegments = listOf("users"),
        queryParams = mapOf("since" to lastUserId, "per_page" to 10)
    )
}