package com.example.rxdemo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

//just operator with individual items
class MainActivity4 : AppCompatActivity() {

    private lateinit var myObservable : Observable<String>
    private lateinit var myObserver: DisposableObserver<String>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        myObservable = Observable.just("Hello A", "Hello B", "Hello C")    //just operator can recognize individual items

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getMyObserver()))
    }

    private fun getMyObserver() : DisposableObserver<String> {
        myObserver = object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: String) {
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