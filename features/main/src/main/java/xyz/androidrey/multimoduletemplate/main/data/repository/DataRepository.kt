package xyz.androidrey.multimoduletemplate.main.data.repository

import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.network.NetworkResult

interface DataRepository {
    suspend fun getUsers(lastUserId: Int): NetworkResult<List<User>>
    suspend fun getProfile(username: String?): NetworkResult<Profile>
}