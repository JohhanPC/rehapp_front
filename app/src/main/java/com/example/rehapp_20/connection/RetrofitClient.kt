package com.example.rehapp_20.connection

import com.example.rehapp_20.apiservice.PhysioApiService
import com.example.rehapp_20.apiservice.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.1.64:8080"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    val physioApiService: PhysioApiService by lazy {
        retrofit.create(PhysioApiService::class.java)
    }

}