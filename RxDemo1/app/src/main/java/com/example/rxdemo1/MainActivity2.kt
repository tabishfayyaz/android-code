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

//composite disposable and subscribeWith
class MainActivity2 : AppCompatActivity() {

    private val greeting = "Hello From RxJava"
    private lateinit var myObservable : Observable<String>
    private lateinit var myObserver: DisposableObserver<String>
    private lateinit var myObserver2: DisposableObserver<String>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        myObservable = Observable.just(greeting)
//        myObservable.subscribeOn(Schedulers.io())   //operators will also be done on IO
//        myObservable.observeOn(AndroidSchedulers.mainThread())


        myObserver = object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: String) {
                Log.d(TAG, "onNext")
                Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        }

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(myObserver))

//        compositeDisposable.add(myObserver)

        myObserver2 = object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: String) {
                Log.d(TAG, "onNext")
                Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        }

        compositeDisposable.add(myObservable.subscribeWith(myObserver2))

//        compositeDisposable.add(myObserver2)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() //just one line to clear multiple disposables
    }
}
