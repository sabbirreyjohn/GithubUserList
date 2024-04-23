package xyz.androidrey.multimoduletemplate.main.data.network

import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import javax.inject.Inject

class ApiHelperImpl @Inject internal constructor(private val userApiInterface: ApiInterface) :
    ApiHelper {
    override suspend fun getUsers(lastUserId: Int): List<User> {
        return userApiInterface.getUsers(lastUserId)
    }

    override suspend fun getProfile(username: String?): Profile {
        return userApiInterface.getProfile(username)
    }


}