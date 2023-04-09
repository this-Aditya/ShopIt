package com.aditya.shopit.fragments.edit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.aditya.shopit.States
import com.aditya.shopit.databinding.FragmentEditBinding
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository
import com.squareup.picasso.Picasso

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val args: EditFragmentArgs by navArgs()
    private lateinit var viewModel: EditViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var product: Products = args.Product
        binding.tvERROR.visibility = View.GONE
        val repository = ShopRepository()
        val viewModelFactory = EditViewModelfactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(EditViewModel::class.java)
        Picasso.get().load(product.image).into(binding.ivDF)
        binding.tvCategorydf.text = product.category
        binding.tvTitiledf.text = product.title
        binding.etDescription.setText(product.description)
        binding.etPrice.setText(product.id.toString())
        binding.btnPut.setOnClickListener {
            try {
                product.price = binding.etPrice.text.toString().toFloat()
            }catch (e:NumberFormatException){
                product.price = 0.0f}
            product.description = binding.etDescription.text.toString()
            viewModel.putProduct(product)}

        binding.btnPatch.setOnClickListener {
            val price = try{binding.etPrice.text.toString().toFloat()}
            catch (e:NumberFormatException){
                0.0f
            }
            val description = binding.etDescription.text.toString()
            viewModel.patchProduct(product.id,description,price)
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deletePost(product.id)
        }
        binding.btnPost.setOnClickListener {
            viewModel.postProduct(product)
        }

        viewModel.stateStatus.observe(viewLifecycleOwner, Observer {state->
            binding.tvERROR.visibility = View.VISIBLE
            when(state){
                States.READY-> {
                    binding.tvERROR.text = "READY"
                    binding.tvERROR.setTextColor(Color.CYAN)
                }
                States.ERROR-> {
                    binding.tvERROR.text = "ERROR"
                    binding.tvERROR.setTextColor(Color.RED)
                }
                States.SENT-> {
                    binding.tvERROR.text = "SENT"
                    binding.tvERROR.setTextColor(Color.GREEN)
                }
            }
        })

    }
}