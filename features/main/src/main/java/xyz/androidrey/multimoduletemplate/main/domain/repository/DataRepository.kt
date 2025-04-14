package xyz.androidrey.multimoduletemplate.main.domain.repository

import xyz.androidrey.multimoduletemplate.main.data.entity.Profile
import xyz.androidrey.multimoduletemplate.main.data.entity.User
import xyz.androidrey.multimoduletemplate.network.NetworkResult

interface DataRepository {
    suspend fun getUsers(lastUserId: Int): NetworkResult<List<User>>
    suspend fun getProfile(username: String?): NetworkResult<Profile>
}