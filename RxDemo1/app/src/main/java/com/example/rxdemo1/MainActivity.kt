package com.example.rxdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

//Observable and Observer
class MainActivity : AppCompatActivity() {

    private val greeting = "Hello From RxJava"
    private lateinit var myObservable : Observable<String>
    private lateinit var myObserver: Observer<String>
    lateinit var disposable : Disposable

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myObservable = Observable.just(greeting)
        myObservable.subscribeOn(Schedulers.io())   //operators will also be done on IO
        myObservable.observeOn(AndroidSchedulers.mainThread())

        myObserver = object : Observer<String> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
                disposable = d
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
        //  dispose the subscription otherwise if observer tries to update user-interface that view might not be there anymore (memory leak)
        //  cancel the work being done
        disposable.dispose()
    }
}
