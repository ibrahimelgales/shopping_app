package com.almatar.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines an product resource.
 */
@Entity(
    tableName = "product_resources",
)
data class ProductEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val quantity: Int,
    val description: String,
    val isProductBought: Boolean,
)