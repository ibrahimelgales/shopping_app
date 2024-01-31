package com.almatar.core.data.model

import android.os.Parcel
import android.os.Parcelable
import com.almatar.core.database.model.ProductEntity


data class Product(
    val id: Long,
    val name: String,
    val quantity: Int,
    val description: String,
    val isProductBought: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(quantity)
        parcel.writeString(description)
        parcel.writeByte(if (isProductBought) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}


fun ProductEntity.asExternalModel() = Product(
    id, name, quantity, description, isProductBought
)

fun Product.asEntity() = ProductEntity(
    id, name, quantity, description, isProductBought
)