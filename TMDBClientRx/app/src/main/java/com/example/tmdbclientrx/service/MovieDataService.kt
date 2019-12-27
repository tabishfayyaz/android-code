package com.example.tmdbclientrx.service

import com.example.tmdbclientrx.model.MovieDBResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataService {

    @GET("movie/popular")
    fun getPopularMoviesWithRx(@Query("api_key") apiKey: String) : Observable<MovieDBResponse>
}