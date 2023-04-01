package com.aditya.shopit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Ratings(
    val rate: Float,
    val count: Int
):Parcelable
