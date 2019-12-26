package com.example.postapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.postapp.model.User
import com.example.postapp.service.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit.setOnClickListener {
            postData()
        }
    }

    private fun postData () {
        val user = User(password = et_password.text.toString(), userEmail = et_email.text.toString())

        val postAppService = RetrofitInstance.getService()
        val call = postAppService.getResult(user)

        et_password.setText("")
        et_email.setText("")

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val userResponse = response.body()

                userResponse?.let {
                    tv_result.text = "Id is:  ${it.id}"
                }
            }
        })
    }
}
