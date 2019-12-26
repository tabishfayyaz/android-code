package com.example.rxdemo1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main19.*

//Making Android UI Rx based via 'com.jakewharton.rxbinding2:rxbinding:2.1.1'
class MainActivity19 : AppCompatActivity() {

    private lateinit var inputText : TextView

    companion object {
        val TAG = "MainActivity19"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main19)

        inputText = findViewById(R.id.etInputField)

        val disposable1 = RxTextView.textChanges(inputText)
            .subscribe { charSequence -> tvInput.setText(charSequence) }


        val disposable2 = RxView.clicks(btnClear)
            .subscribe { obj ->
                inputText.text = ""
                tvInput.text = ""
            }
    }
}