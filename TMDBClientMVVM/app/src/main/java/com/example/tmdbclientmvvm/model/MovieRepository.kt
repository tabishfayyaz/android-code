package com.example.tmdbclientmvvm.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.tmdbclientmvvm.service.RetrofitInstance
import com.example.tmdbclientrx.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MovieRepository(application: Application) {

    private val movies = mutableListOf<Movie>()
    private val movieDBResponseObservable : Observable<MovieDBResponse>

    private val compositeDisposable = CompositeDisposable()
    val moviesLiveData : MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        val movieDataService = RetrofitInstance.getService()

        movieDBResponseObservable = movieDataService.getPopularMoviesWithRx(application.applicationContext.getString(
            R.string.api_key))

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
                        moviesLiveData.postValue(movies)
                    }

                    override fun onNext(movie: Movie) {
                        movies.add(movie)
                    }

                    override fun onError(e: Throwable) {
                    }
                }))
    }

    fun clear() {
        compositeDisposable.clear()
    }
}