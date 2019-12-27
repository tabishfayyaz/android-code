package com.example.tmdbclientrx.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbclientrx.R
import com.example.tmdbclientrx.adapter.MovieAdapter
import com.example.tmdbclientrx.model.Movie
import com.example.tmdbclientrx.model.MovieDBResponse
import com.example.tmdbclientrx.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val movies = mutableListOf<Movie>()

    companion object {
        const val TAG = "MainActivity"
        const val API_KEY = "6679d5ba4788175ba3e643fe6fa15b55"  //be nice and get your own API KEY: https://www.themoviedb.org
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "TMDB Popular Movies Today"

        getPopularMoviesRx()
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            getPopularMoviesRx()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun getPopularMoviesRx() {
        val movieDataService = RetrofitInstance.getService()

        val movieDBResponseObservable = movieDataService.getPopularMoviesWithRx(API_KEY)

        compositeDisposable.add(
        movieDBResponseObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { movieDBResponse: MovieDBResponse ->
                Observable.fromArray(*movieDBResponse.movies.toTypedArray())
            }
            .filter { movie ->
                movie.voteAverage > 7.0
            }
            .subscribeWith(object : DisposableObserver<Movie>(){
                override fun onComplete() {
                    showOnRecyclerView()
                    swipeRefreshLayout.isRefreshing = false
                }

                override fun onNext(movie: Movie) {
                    movies.add(movie)
                }

                override fun onError(e: Throwable) {
                }
            }))

    }

    fun showOnRecyclerView() {

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
