package com.example.postapp.service

import com.example.postapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostAppService {

    @POST("posts")
    fun getResult(@Body user: User) : Call<User>
}