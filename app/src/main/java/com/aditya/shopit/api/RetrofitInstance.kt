package com.aditya.shopit.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val BASE_URL = "https://fakestoreapi.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: ShopApi by lazy {
        retrofit.create(ShopApi::class.java)
    }
}