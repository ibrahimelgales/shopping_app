/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.almatar.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.almatar.core.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [ProductEntity] access
 */
@Dao
interface ProductDao {


    @Query(
        value = """
        SELECT * FROM product_resources
        ORDER BY CASE WHEN :isAsc = 1 THEN id END ASC, CASE WHEN :isAsc = 0 THEN id END DESC
    """,
    )
    fun getAllProducts( isAsc : Boolean): Flow<List<ProductEntity>>

    @Query(
        value = """
        SELECT * FROM product_resources
        WHERE id IN (:ids)
        ORDER BY CASE WHEN :isAsc = 1 THEN id END ASC, CASE WHEN :isAsc = 0 THEN id END DESC
    """,
    )
    fun getProductEntities(ids: Set<Long>, isAsc : Boolean): Flow<List<ProductEntity>>

    @Upsert
    suspend fun insertOrReplaceProduct(entity: ProductEntity)

    @Query(value = "DELETE FROM product_resources WHERE id == :targetId")
    suspend fun deleteProductById(targetId : Long)

}
