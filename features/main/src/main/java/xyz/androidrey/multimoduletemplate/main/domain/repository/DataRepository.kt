package xyz.androidrey.multimoduletemplate.main.domain.repository

import xyz.androidrey.multimoduletemplate.main.data.network.ApiHelper
import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.main.domain.util.Status
import javax.inject.Inject

class DataRepository @Inject internal constructor(private val userApiHelper: ApiHelper) {

    suspend fun getUsersFromServer(sinceId: Int): Status<List<User>> {
        val response = try {
            userApiHelper.getUsers(sinceId)
        } catch (e: Exception) {
            return Status.Error("An unknown error occured.")
        }
        return Status.Success(response)
    }

    suspend fun getProfileFromServer(username: String?): Status<Profile> {
        val response = try {
            userApiHelper.getProfile(username)
        } catch (e: Exception) {
            return Status.Error("An unknown error occured.")
        }
        return Status.Success(response)
    }
}