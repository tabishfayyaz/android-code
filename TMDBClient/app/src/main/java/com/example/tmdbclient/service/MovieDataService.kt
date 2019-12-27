package com.example.tmdbclient.service

import com.example.tmdbclient.model.MovieDBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String) : Call<MovieDBResponse>
}