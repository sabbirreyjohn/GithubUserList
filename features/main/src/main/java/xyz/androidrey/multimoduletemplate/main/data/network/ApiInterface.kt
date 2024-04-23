package xyz.androidrey.multimoduletemplate.main.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.androidrey.multimoduletemplate.main.domain.entity.Profile
import xyz.androidrey.multimoduletemplate.main.domain.entity.User

interface ApiInterface {
    @GET("users")
    suspend fun getUsers(@Query("since") lastUserId: Int): List<User>

    @GET("users/{USER_NAME}")
    suspend fun getProfile(@Path("USER_NAME") username: String?): Profile
}