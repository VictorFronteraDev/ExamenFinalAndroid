package com.example.fronteravictor_examenfinal.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Player (@SerializedName("id") @Expose var id: Int?,
                   @SerializedName("name") @Expose var name: String,
                   @SerializedName("surname") @Expose var surname: String,
                   @SerializedName("years") @Expose var years: Int,
                   @SerializedName("teams") @Expose var teams: ArrayList<String>) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeValue(years)
        parcel.writeArray(arrayOf(teams))

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}