package com.example.rxdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

//buffer operator
class MainActivity10 : AppCompatActivity() {

    private lateinit var myObservable : Observable<Int>
    private lateinit var myObserver: DisposableObserver<List<Int>>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity10"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        myObservable = Observable.range(1, 20)

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .buffer(4)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getMyObserver()))
    }

    private fun getMyObserver() : DisposableObserver<List<Int>> {
        myObserver = object : DisposableObserver<List<Int>>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: List<Int>) {
                Log.d(TAG, "onNext $s")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        }
        return myObserver
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() //just one line to clear multiple disposables
    }
}