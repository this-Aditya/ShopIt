package com.aditya.shopit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.shopit.databinding.ExampleItemBinding
import com.aditya.shopit.diffutils.ProductsDiffutil
import com.aditya.shopit.models.Products
import com.squareup.picasso.Picasso

class ProductsAdapter(private val context: Context,val itemclicked:ItemClickedListener) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    private var products: List<Products> = emptyList()
    private lateinit var binding: ExampleItemBinding

    interface ItemClickedListener{
        fun onItemClicked()
    }

    class ProductsViewHolder(val binding: ExampleItemBinding,val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(product: Products) {
            val price = product.price.toString()
            val count = product.rating.count.toString()
            binding.ratingBar.rating = product.rating.rate
            binding.tvId.text = product.id.toString()
            binding.tvCount.text = "$count left"
            binding.tvDescription.text = product.description
            binding.tvPrice.text = "$$price"
            binding.tvTitle.text = product.title
            Picasso.with(context).load(product.image).into(binding.ivItem)
        }
    }

        private fun loadImage(product: Products) {
            Picasso.with(context).load(product.image).into(binding.ivItem)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        binding = ExampleItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductsViewHolder(binding,context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindData(products[position])
        holder.binding.clExample.setOnClickListener {
            itemclicked.onItemClicked()
        }
    }

    fun setData(updatedProducts: List<Products>) {
        val diffUtil = ProductsDiffutil(products, updatedProducts)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        products = updatedProducts
        diffResults.dispatchUpdatesTo(this)
    }

}
