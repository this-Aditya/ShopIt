package com.aditya.shopit.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.Products
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private var _products: MutableLiveData<List<Products>> = MutableLiveData()
    val products: LiveData<List<Products>>
        get() = _products

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?>
        get() = _error


    fun getProducts() {
        viewModelScope.launch {
            try {
                _error.value = null
                _products.value = RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

}