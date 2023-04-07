package com.aditya.shopit.fragments.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository
import kotlinx.coroutines.launch
import retrofit2.Response
private const val TAG = "ListViewModel"
class ListViewModel(val repository: ShopRepository) : ViewModel() {

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
                val response:Response<List<Products>> = repository.getProducts()
                if(response.isSuccessful){
                    _products.value = response.body()
                }else{
                    Log.i(TAG, response.errorBody().toString())
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

}