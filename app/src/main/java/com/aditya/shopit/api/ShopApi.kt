package com.aditya.shopit.api

import com.aditya.shopit.models.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {
    @GET("products")
    suspend fun getProducts(): List<Products>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") productId: Int
    ):Products
}