package com.aditya.shopit.repository

import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.PatchProduct
import com.aditya.shopit.models.Products
import retrofit2.Response

class ShopRepository {

    suspend fun getProducts(): Response<List<Products>> = RetrofitInstance.api.getProducts()

    suspend fun getProduct(productId: Int): Response<Products> =
        RetrofitInstance.api.getProduct(productId)

    suspend fun postProduct(body: Products): Products =
        RetrofitInstance.api.postProduct(body)

    suspend fun deleteProduct(productId: Int): Products =
        RetrofitInstance.api.deleteProduct(productId)


    suspend fun patchProduct(productId: Int, change: PatchProduct): PatchProduct =
        RetrofitInstance.api.patchProduct(productId, change)

 suspend fun putProduct(productId: Int, change: Products): Products =
        RetrofitInstance.api.putProduct(productId, change)


}