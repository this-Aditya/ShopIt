package com.aditya.shopit.fragments.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.aditya.shopit.databinding.FragmentDetailBinding
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository
import com.squareup.picasso.Picasso

private const val TAG = "DetailFragment"

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs();
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelfactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        val productId = args.productId
        val repository = ShopRepository()
        val viewModelfactory = DetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelfactory).get(DetailViewModel::class.java)

        viewModel.getProduct(productId)
        Log.i(TAG, "Product ID $productId ")

        viewModel.product.observe(viewLifecycleOwner, Observer {product->
            val price = product.price.toString()
            binding.tvTitiledf.text = product.title
            binding.tvDescriptiondf.text = product.description
            binding.tvCategorydf.text = product.category
            binding.tvPricedf.text = "$$price"
            Picasso.get().load(product.image).into(binding.ivDF)
            binding.btnEditdf.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(product)
                Log.i(TAG, "$productId sending value via navArgs from detail to edit fragments.")
                view.findNavController().navigate(action)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {error->
            if (error==null){
                binding.tvERROR.visibility = View.GONE
            }
            else{
                Log.i(TAG, error)
//                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                binding.tvERROR.visibility = View.VISIBLE
                Log.i(TAG, "$error")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}