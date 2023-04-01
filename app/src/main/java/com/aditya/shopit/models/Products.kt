package com.aditya.shopit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val id: Int,
    val title: String,
    var price: Float,
    var description: String,
    val category: String,
    val image: String,
    val rating: Ratings
):Parcelable
