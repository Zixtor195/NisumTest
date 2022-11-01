package com.example.nisumtest.core.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonItemListDetails(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonItemListDetails> {
        override fun createFromParcel(parcel: Parcel): PokemonItemListDetails {
            return PokemonItemListDetails(parcel)
        }

        override fun newArray(size: Int): Array<PokemonItemListDetails?> {
            return arrayOfNulls(size)
        }
    }
}