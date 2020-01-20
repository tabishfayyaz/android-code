package com.example.tmdbclientmvvm.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbclientmvvm.adapter.MovieAdapter
import com.example.tmdbclientmvvm.model.Movie
import com.example.tmdbclientrx.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var movies : List<Movie>
    private lateinit var mainActivityViewModel : MainActivityViewModel

    override fun onDestroy() {
        super.onDestroy()
        mainActivityViewModel.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "TMDB MVVM Popular Movies Today"

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        getPopularMoviesRx()
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            getPopularMoviesRx()
        }
    }

    private fun getPopularMoviesRx() {

        mainActivityViewModel.getAllMovies().observe(this, object : Observer<List<Movie>>{
            override fun onChanged(movieList: List<Movie>?) {
                movies = movieList as MutableList<Movie>
                init()
            }
        })
    }

    fun init() {

        val adapter = MovieAdapter(applicationContext, movies)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvMovies.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvMovies.layoutManager = GridLayoutManager(this, 4)
        }

        rvMovies.itemAnimator = DefaultItemAnimator()
        rvMovies.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}