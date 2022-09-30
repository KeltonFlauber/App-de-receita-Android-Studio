package com.example.receitasrapidas

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class Receita(
    val img : Int,
    val title : String,
    val ingredientes : String,
    val preparo : String,
    val category : String,
    val category2 : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(img)
        parcel.writeString(title)
        parcel.writeString(ingredientes)
        parcel.writeString(preparo)
        parcel.writeString(category)
        parcel.writeString(category2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Receita> {
        override fun createFromParcel(parcel: Parcel): Receita {
            return Receita(parcel)
        }

        override fun newArray(size: Int): Array<Receita?> {
            return arrayOfNulls(size)
        }
    }
}