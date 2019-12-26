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

//distinct operator
class MainActivity12 : AppCompatActivity() {

    private val numbs = arrayOf(1,2,3,3,3,4,4,4,5,6,7,7,8,9)
    private lateinit var myObservable : Observable<Int>
    private lateinit var myObserver: DisposableObserver<Int>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity12"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // When we call a vararg-function, we can pass arguments one-by-one, e.g. asList(1, 2, 3), or, if we already have an array and want to pass its contents to the function, we use the spread operator (prefix the array with *)
        myObservable = Observable.fromArray(*numbs)

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .distinct()
            .subscribeWith(getMyObserver()))
    }

    private fun getMyObserver() : DisposableObserver<Int> {
        myObserver = object : DisposableObserver<Int>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: Int) {
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