package xyz.androidrey.multimoduletemplate.main.data.network

import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User

interface ApiHelper {
    suspend fun getUsers(lastUserId: Int): List<User>
    suspend fun getProfile(username: String?): Profile
}