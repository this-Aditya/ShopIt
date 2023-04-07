package com.aditya.shopit.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.shopit.adapters.ProductsAdapter
import com.aditya.shopit.databinding.FragmentListBinding
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository

private const val TAG = "ListFragment"

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var viewmodelfactory:ListViewModelFactory
    private lateinit var adapter: ProductsAdapter
    private val shopingProducts = mutableListOf<Products>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        val repository = ShopRepository()
        viewmodelfactory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewmodelfactory).get(ListViewModel::class.java)

        setupRecyclerView()

        viewModel.getProducts()
        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            shopingProducts.clear()
            shopingProducts.addAll(products)
            adapter.setData(shopingProducts)
            shopingProducts.forEach {it-> Log.i(TAG, "${it.id}") }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {error->
            if (error==null) {
                return@Observer
            }
            else{
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        val view = binding.root
        adapter = ProductsAdapter(requireContext(),object :ProductsAdapter.ItemClickedListener{
            override fun onItemClicked(product: Products) {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(product.id)
              view.findNavController().navigate(action)
            }
        })
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }
}