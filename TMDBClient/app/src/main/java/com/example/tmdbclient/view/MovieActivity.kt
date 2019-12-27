package com.example.tmdbclient.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbclient.R
import com.example.tmdbclient.model.Movie
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("movie")){
            val movie = intent.getParcelableExtra<Movie>("movie")

            val imagePath = "https://image.tmdb.org/t/p/w500" + movie.posterPath

            Picasso.get().load(imagePath).placeholder(R.drawable.loading).into(ivMovieLarge)

            supportActionBar?.title = movie.title

            tvMovieTitle.text = movie.title
            tvPlotsynopsis.text = movie.overview
            tvMovieRating.text = movie.voteAverage.toString()
            tvReleaseDate.text = movie.releaseDate
        }
    }
}