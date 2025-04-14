package xyz.androidrey.multimoduletemplate.main.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.main.data.source.local.dao.ProductDao

@Database(entities = [Product::class], version = 1)

abstract class TheDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}