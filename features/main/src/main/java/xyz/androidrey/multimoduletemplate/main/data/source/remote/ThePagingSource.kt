package xyz.androidrey.multimoduletemplate.main.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.data.entity.User
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import javax.inject.Inject

class ThePagingSource @Inject constructor(private val repository: DataRepository) :
    PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        var since = params.key ?: 0
        val response = repository.getUsers(since)

        when (response) {
            is NetworkResult.Error -> return LoadResult.Error(IllegalStateException(response.exception.message))
            is NetworkResult.Success -> {
                val data = response.result
                val nextKey = if (data.isEmpty()) null else data.last().userId + 1
                return LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = nextKey
                )
            }
        }

    }
}