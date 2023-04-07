package com.aditya.shopit.repository

import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.Products
import retrofit2.Response

class ShopRepository {

    suspend fun getProducts(): Response<List<Products>> = RetrofitInstance.api.getProducts()

    suspend fun getProduct(productId: Int): Response<Products> =
        RetrofitInstance.api.getProduct(productId)


}