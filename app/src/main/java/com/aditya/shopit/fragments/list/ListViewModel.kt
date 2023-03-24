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
    val products : LiveData<List<Products>>
    get() = _products

    fun getProducts(){
        viewModelScope.launch {
            _products.value = RetrofitInstance.api.getProducts()
        }
    }

}