package com.aditya.shopit.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.shopit.adapters.ProductsAdapter
import com.aditya.shopit.databinding.FragmentListBinding
import com.aditya.shopit.models.Products

private lateinit var binding: FragmentListBinding
private lateinit var viewModel: ListViewModel
private const val TAG = "ListFragment"

class ListFragment : Fragment() {
    private lateinit var adapter: ProductsAdapter
    private val shopingProducts = mutableListOf<Products>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        setupRecyclerView()

        viewModel.getProducts()
        viewModel.products.observe(requireActivity(), Observer { products ->
            shopingProducts.addAll(products)
            adapter.setData(shopingProducts)
            shopingProducts.forEach {it-> Log.i(TAG, "${it.id}") }
        })
    }

    private fun setupRecyclerView() {
        adapter = ProductsAdapter(requireContext(),object :ProductsAdapter.ItemClickedListener{
            override fun onItemClicked() {
            }

        })
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
    }
}