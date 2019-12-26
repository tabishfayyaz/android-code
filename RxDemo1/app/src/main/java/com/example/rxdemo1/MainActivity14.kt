package com.example.rxdemo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject

// A Subject is a sort of bridge or proxy that acts both as an observer and as an Observable.
// Because it is an observer, it can subscribe to one or more Observables, and because it is an Observable,
// it can pass through the items it observes by re-emitting them, and it can also emit new items.

// AsyncSubject emits the last value of the source Observable(and only the last value) only after that source Observable completes.
class MainActivity14 : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity14"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        asyncSubjectDemo1()
    }

    private fun asyncSubjectDemo1() {

        val observable = Observable.just("Java", "Kotlin", "Xml", "JSON")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val asyncSubject = AsyncSubject.create<String>()
        observable.subscribe(asyncSubject)  //asyncSubject acting as an Observer

        // all observers will only get JSON
        asyncSubject.subscribe(getFirstObserver())  //asyncSubject acting as an Observable
        asyncSubject.subscribe(getSecondObserver())
        asyncSubject.subscribe(getThirdObserver())
    }

    private fun getFirstObserver() : Observer<String> {
        return object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "First Observer onSubscribe")
            }

            override fun onComplete() {
                Log.d(TAG, "First Observer onComplete")
            }

            override fun onNext(s:String) {
                Log.d(TAG, "First Observer onNext $s")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "First Observer onError")
            }
        }
    }

    private fun getSecondObserver() : Observer<String> {
        return object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "Second Observer onSubscribe")
            }

            override fun onComplete() {
                Log.d(TAG, "Second Observer onComplete")
            }

            override fun onNext(s:String) {
                Log.d(TAG, "Second Observer onNext $s")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Second Observer onError")
            }
        }
    }

    private fun getThirdObserver() : Observer<String> {
        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "Third Observer onSubscribe")
            }

            override fun onComplete() {
                Log.d(TAG, "Third Observer onComplete")
            }

            override fun onNext(s:String) {
                Log.d(TAG, "Third Observer onNext $s")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Third Observer onError")
            }
        }

        return observer
    }
}