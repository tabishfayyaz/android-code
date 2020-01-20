package com.example.tmdbclientmvvm.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tmdbclientmvvm.model.Movie
import com.example.tmdbclientmvvm.model.MovieRepository

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {

    private val movieRepository : MovieRepository = MovieRepository(application)

    fun getAllMovies() : LiveData<List<Movie>> {
        return movieRepository.moviesLiveData
    }

    fun clear() {
        movieRepository.clear()
    }
}