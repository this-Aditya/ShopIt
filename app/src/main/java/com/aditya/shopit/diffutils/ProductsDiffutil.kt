package com.aditya.shopit.diffutils

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.aditya.shopit.models.Products

private const val TAG = "Diffutil"
class ProductsDiffutil(
    private val oldProducts: List<Products>,
    private val newProducts: List<Products>
) : DiffUtil.Callback() {
    init {
        Log.i(TAG, "Diffuitls: ")
    }
    override fun getOldListSize(): Int {
        return oldProducts.size
    }

    override fun getNewListSize(): Int {
        return newProducts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.i(TAG, "oldPosition: $oldItemPosition newPosition $newItemPosition ")
        Log.i(TAG, "oldId ${oldProducts[oldItemPosition].id} newId: ${newProducts[newItemPosition].id}")
        return (oldProducts[oldItemPosition].id == newProducts[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProducts[oldItemPosition] == newProducts[newItemPosition]
    }
}