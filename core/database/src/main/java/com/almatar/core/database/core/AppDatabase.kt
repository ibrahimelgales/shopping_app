package com.almatar.core.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.almatar.core.database.dao.ProductDao
import com.almatar.core.database.dao.ProductDaoFts
import com.almatar.core.database.model.ProductEntity
import com.almatar.core.database.model.ProductEntityFts

@Database(
    entities = [
        ProductEntity::class,
        ProductEntityFts::class,
    ],
    version = 1,
    exportSchema = false,
)


internal abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productDaoFts(): ProductDaoFts
}
