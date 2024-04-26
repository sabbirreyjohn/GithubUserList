package xyz.androidrey.multimoduletemplate.main.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import xyz.androidrey.multimoduletemplate.main.data.local.dao.TheDatabase
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject internal constructor(
    private val database: TheDatabase,
    private val requestHandler: RequestHandler
) : RemoteMediator<Int, User>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.userId ?: 0
                }
            }
            val users = requestHandler.get<List<User>>(
                urlPathSegments = listOf("users"),
                queryParams = mapOf("since" to loadKey, "per_page" to 10)
            )
            when (users) {
                is NetworkResult.Error -> {
                    MediatorResult.Error(users.exception)
                }
                is NetworkResult.Success -> {
                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            database.userDao.clearAll()
                        }
                        database.userDao.insertAll(users.result)
                    }
                    MediatorResult.Success(endOfPaginationReached = users.result.isEmpty())
                }
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}