package com.example.rxdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

//map operator: if you want a non-observable object as output (also see MainActivity9)
class MainActivity8 : AppCompatActivity() {

    private lateinit var myObservable : Observable<Student>
    private lateinit var myObserver: DisposableObserver<Student>

    private val compositeDisposable = CompositeDisposable() //when you want to dispose multiple observers with just one call

    companion object {
        val TAG = "MainActivity8"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val observableOnSubscribe = object : ObservableOnSubscribe<Student> {
            override fun subscribe(emitter: ObservableEmitter<Student>) {
                for (student in getStudents()){
                    emitter.onNext(student)
                }
                emitter.onComplete()
            }
        }

        myObservable = Observable.create(observableOnSubscribe)

        compositeDisposable.add(
        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { student : Student ->
                student.name = student.name.toUpperCase(Locale.getDefault())
                student
            }
            .subscribeWith(getMyObserver()))
    }

    private fun getMyObserver() : DisposableObserver<Student> {
        myObserver = object : DisposableObserver<Student>(){
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(s: Student) {
                Log.d(TAG, "onNext ${s.name}")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        }
        return myObserver
    }

    private fun getStudents() : List<Student> {
        return listOf<Student>(Student("Student 1", "student1@gmail.com", 28),
            Student("Student 2", "student2@gmail.com", 29),
            Student("Student 3", "student3@gmail.com", 30),
            Student("Student 4", "student4@gmail.com", 31),
            Student("Student 5", "student5@gmail.com", 32))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() //just one line to clear multiple disposables
    }

    data class Student (var name:String, var email:String, var age:Int)
}