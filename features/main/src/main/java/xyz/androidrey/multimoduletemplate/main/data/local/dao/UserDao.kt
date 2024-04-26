package xyz.androidrey.multimoduletemplate.main.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.androidrey.multimoduletemplate.main.domain.entity.User

@Dao
interface UserDao {
    @Query("select * from User")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM User")
    fun getUsersPaging(): PagingSource<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM User")
    suspend fun clearAll()
}