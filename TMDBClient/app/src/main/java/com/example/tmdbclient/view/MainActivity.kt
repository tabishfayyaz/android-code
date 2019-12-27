package com.example.tmdbclient.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbclient.adapter.MovieAdapter
import com.example.tmdbclient.R
import com.example.tmdbclient.model.Movie
import com.example.tmdbclient.model.MovieDBResponse
import com.example.tmdbclient.service.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val API_KEY = "6679d5ba4788175ba3e643fe6fa15b55"  //be nice and get your own API KEY: https://www.themoviedb.org
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "TMDB Popular Movies Today"

        getPopularMovies()
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            getPopularMovies()
        }
    }

    private fun getPopularMovies() {
        val movieDataService = RetrofitInstance.getService()

        val call = movieDataService.getPopularMovies(API_KEY)
        call.enqueue(object : Callback<MovieDBResponse> {
            override fun onFailure(call: Call<MovieDBResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieDBResponse>, response: Response<MovieDBResponse>) {
                val movieDBResponse = response.body()

                val movies : List<Movie>? = movieDBResponse?.movies
                showOnRecyclerView(movies)
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    fun showOnRecyclerView(movies : List<Movie>?) {
        movies?.let {

            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvMovies.layoutManager = GridLayoutManager(this, 2)
            } else {
                rvMovies.layoutManager = GridLayoutManager(this, 4)
            }

            val adapter = MovieAdapter(applicationContext, movies)
            rvMovies.itemAnimator = DefaultItemAnimator()
            rvMovies.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}
