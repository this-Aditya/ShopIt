package com.aditya.shopit.api

import com.aditya.shopit.models.Products
import retrofit2.http.GET

interface ShopApi {
    @GET("products")
    suspend fun getProducts():List<Products>
}