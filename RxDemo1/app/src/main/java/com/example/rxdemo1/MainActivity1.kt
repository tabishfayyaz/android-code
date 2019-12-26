package com.example.rxdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

//disposable observer
class MainActivity1 : AppCompatActivity() {

    private val greeting = "Hello From RxJava 1"
    private lateinit var myObservable : Observable<String>
    private lateinit var myObserver: DisposableObserver<String> //better way to dispose especially if you have multiple observers, it implements both Observer and Disposable interface

    companion object {
        val TAG = "MainActivity1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        myObservable = Observable.just(greeting)
        myObservable.subscribeOn(Schedulers.io())   //operators will also be done on IO
        myObservable.observeOn(AndroidSchedulers.mainThread())

        //3 overridden methods as oppose to 4 in Observer as no need for onSubscribe method anymore
        myObserver = object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: String) {
                Log.d(TAG, "onNext")
                tvGreeting.text = s
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        }

        myObservable.subscribe(myObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        myObserver.dispose()
    }
}
