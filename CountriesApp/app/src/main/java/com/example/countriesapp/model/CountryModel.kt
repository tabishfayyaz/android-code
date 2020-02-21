package com.example.countriesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryModel(
    @SerializedName("name") val countryName: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("flagPNG") val flag: String
) : Parcelable