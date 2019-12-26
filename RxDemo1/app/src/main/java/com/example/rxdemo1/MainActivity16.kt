package com.example.rxdemo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

// A Subject is a sort of bridge or proxy that acts both as an observer and as an Observable.
// Because it is an observer, it can subscribe to one or more Observables, and because it is an Observable,
// it can pass through the items it observes by re-emitting them, and it can also emit new items.

// BehaviorSubject: emits the most recently emitted item and all the subsequent items
class MainActivity16 : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity16"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

//        behaviorSubjectDemo1()
        behaviorSubjectDemo2()
    }

    private fun behaviorSubjectDemo1() {

        val observable = Observable.just("Java", "Kotlin", "Xml", "JSON")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val behaviorSubject = BehaviorSubject.create<String>()
        observable.subscribe(behaviorSubject)  //behaviorSubject acting as an Observer

        // all three observers subscribe to observable at beginning of emission so each should receive all 4 emitted items
        behaviorSubject.subscribe(getFirstObserver())  //behaviorSubject acting as an Observable
        behaviorSubject.subscribe(getSecondObserver())
        behaviorSubject.subscribe(getThirdObserver())
    }

    private fun behaviorSubjectDemo2() {

        val behaviorSubject = BehaviorSubject.create<String>()

        behaviorSubject.subscribe(getFirstObserver())  //behaviorSubject acting as an Observable
        behaviorSubject.onNext("Java")
        behaviorSubject.onNext("Kotlin")
        behaviorSubject.onNext("Xml")

        behaviorSubject.subscribe(getSecondObserver())
        behaviorSubject.onNext("LAST VALUE")
        behaviorSubject.onComplete()

        behaviorSubject.subscribe(getThirdObserver())
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