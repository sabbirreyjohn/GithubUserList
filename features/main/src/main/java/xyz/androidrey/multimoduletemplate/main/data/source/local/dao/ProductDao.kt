package xyz.androidrey.multimoduletemplate.main.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.androidrey.multimoduletemplate.main.data.entity.Product

@Dao
interface ProductDao {
    @Query("select * from Product")
    suspend fun getProducts(): List<Product>

    @Query("SELECT * FROM Product")
    fun getProductPaging(): PagingSource<Int, Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM Product")
    suspend fun clearAll()
}