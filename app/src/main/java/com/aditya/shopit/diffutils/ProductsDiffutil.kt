package com.aditya.shopit.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.shopit.models.Products

class ProductsDiffutil(
    private val oldProducts: List<Products>,
    private val newProducts: List<Products>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldProducts.size
    }

    override fun getNewListSize(): Int {
        return newProducts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldProducts[oldItemPosition].id == newProducts[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProducts[oldItemPosition] == newProducts[newItemPosition]
    }
}