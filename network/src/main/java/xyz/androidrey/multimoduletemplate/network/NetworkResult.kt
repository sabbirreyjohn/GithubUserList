package xyz.androidrey.multimoduletemplate.network

sealed class NetworkResult<out T> {
    data class Success<T>(val result: T) : NetworkResult<T>()
    data class Error<Nothing>(val body: String?, val exception: NetworkException) :
        NetworkResult<Nothing>()
}