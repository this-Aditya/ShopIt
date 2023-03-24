package com.aditya.shopit.models


data class Products(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String,
    val rating: Ratings
)
