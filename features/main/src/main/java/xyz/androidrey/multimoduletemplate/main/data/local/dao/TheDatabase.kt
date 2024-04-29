package xyz.androidrey.multimoduletemplate.main.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.androidrey.multimoduletemplate.main.domain.entity.Product

@Database(entities = [Product::class], version = 1)

abstract class TheDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}