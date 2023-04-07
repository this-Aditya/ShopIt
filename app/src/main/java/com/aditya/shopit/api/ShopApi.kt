package com.aditya.shopit.api

import com.aditya.shopit.models.PatchProduct
import com.aditya.shopit.models.Products
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ShopApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Products>>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") productId: Int
    ): Response<Products>

    @PUT("products/{id}")
    suspend fun putProduct(
        @Path("id") productId: Int,
        @Body product: Products
    ): Products

    @PATCH("products/{id}")
    suspend fun patchProduct(
        @Path("id") productId: Int,
        @Body params: PatchProduct
    ): Products

    @POST("products")
    suspend fun postProduct(
        @Body product:Products
    ):Products

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") productId:Int
    ):Products
}