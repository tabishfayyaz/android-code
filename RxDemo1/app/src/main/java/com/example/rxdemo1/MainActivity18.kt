package com.example.rxdemo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

// A Subject is a sort of bridge or proxy that acts both as an observer and as an Observable.
// Because it is an observer, it can subscribe to one or more Observables, and because it is an Observable,
// it can pass through the items it observes by re-emitting them, and it can also emit new items.

// ReplaySubject: emits all the items of the Observable without considering when the subscriber subscribed
class MainActivity18 : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity18"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

//        replaySubjectDemo1()
        replaySubjectDemo2()
    }

    private fun replaySubjectDemo1() {

        val observable = Observable.just("Java", "Kotlin", "Xml", "JSON")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val replaySubject = ReplaySubject.create<String>()
        observable.subscribe(replaySubject)  //replaySubject acting as an Observer

        replaySubject.subscribe(getFirstObserver())  //replaySubject acting as an Observable
        replaySubject.subscribe(getSecondObserver())
        replaySubject.subscribe(getThirdObserver())
    }

    private fun replaySubjectDemo2() {

        val replaySubject = ReplaySubject.create<String>()

        replaySubject.subscribe(getFirstObserver())  //replaySubject acting as an Observable
        replaySubject.onNext("Java")
        replaySubject.onNext("Kotlin")
        replaySubject.onNext("Xml")

        replaySubject.subscribe(getSecondObserver())
        replaySubject.onNext("LAST VALUE")
        replaySubject.onComplete()

        replaySubject.subscribe(getThirdObserver())
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