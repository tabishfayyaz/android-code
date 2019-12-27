package com.example.tmdbclient.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDBResponse (
    @SerializedName("page") val page : Int,
    @SerializedName("total_results")  val totalMovies : Int,
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("results") val movies : List<Movie>
) : Parcelable