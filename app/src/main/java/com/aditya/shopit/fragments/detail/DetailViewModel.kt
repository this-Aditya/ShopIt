package com.aditya.shopit.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.Products
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _product = MutableLiveData<Products>()
    val product: LiveData<Products>
        get() = _product

    private val _error = MutableLiveData<String?>(null)
    val error :LiveData<String?>
    get() = _error

    fun getProduct(id: Int) {
        viewModelScope.launch {
            try {

                _error.value = null
                _product.value = RetrofitInstance.api.getProduct(id)
            }
            catch (e:Exception){
                _error.value = e.message
            }
        }
    }

}