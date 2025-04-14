package xyz.androidrey.multimoduletemplate.main.data.repository

import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.data.entity.Profile
import xyz.androidrey.multimoduletemplate.main.data.entity.User
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import javax.inject.Inject

class DataRepositoryImpl @Inject internal constructor(private val requestHandler: RequestHandler) :
    DataRepository {
    override suspend fun getUsers(lastUserId: Int) = requestHandler.get<List<User>>(
        urlPathSegments = listOf("users"),
        queryParams = mapOf("since" to lastUserId, "per_page" to 10)
    )
    override suspend fun getProfile(username: String?) =
        requestHandler.get<Profile>(urlPathSegments = listOf("users", username!!))
    }