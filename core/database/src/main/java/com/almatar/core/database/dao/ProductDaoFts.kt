package com.almatar.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.almatar.core.database.model.ProductEntity
import com.almatar.core.database.model.ProductEntityFts
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [ProductEntity] access
 */
@Dao
interface ProductDaoFts {


    @Query("SELECT product_id_fts FROM product_resources_fts WHERE product_resources_fts MATCH :searchQuery")
    fun searchAllProducts(searchQuery: String): Flow<List<Long>>

    @Upsert
    suspend fun insertOrReplaceProduct(entity: ProductEntityFts)

    @Query(value = "DELETE FROM product_resources_fts WHERE product_id_fts == :targetId")
    suspend fun deleteProductById(targetId: Long)

}
