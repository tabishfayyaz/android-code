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

//just operator with single array
class MainActivity3 : AppCompatActivity() {

    private val greeting = arrayOf("Hello A", "Hello B", "Hello C")
    private lateinit var myObservable : Observable<Array<String>>
    private lateinit var myObserver: DisposableObserver<Array<String>>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        myObservable = Observable.just(greeting)    //just operator will emit the whole array as single item

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getMyObserver()))
    }

    private fun getMyObserver() : DisposableObserver<Array<String>> {
        myObserver = object : DisposableObserver<Array<String>>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: Array<String>) {
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