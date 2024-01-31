package com.almatar.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.almatar.core.database.core.AppDatabase
import com.almatar.core.database.model.ProductEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductDaoTest {

    private lateinit var productDao: ProductDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        productDao = db.productDao()
    }

    @Test
    fun productDao_insure_data_inserted_was_saved() = runTest {
        val id = 1L
        val insertedEntity = ProductEntity(
            id,
            "falafel",
            1,
            "any description",
            true,
        )
        productDao.insertOrReplaceProduct(
            insertedEntity,
        )

        val savedNEntity = productDao.getProductEntities(setOf(id), true)
            .first().first()

        assertEquals(
            savedNEntity, insertedEntity
        )
    }
}