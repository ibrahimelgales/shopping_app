package com.almatar.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "product_resources_fts")
@Fts4
data class ProductEntityFts(

    @ColumnInfo(name = "product_id_fts")
    val productId: Long,

    @ColumnInfo(name = "name_fts")
    val name: String,

    @ColumnInfo(name = "description_fts")
    val description: String,
)

fun ProductEntity.asEntityFts() = ProductEntityFts(
    productId = id,
    name = name,
    description = description
)