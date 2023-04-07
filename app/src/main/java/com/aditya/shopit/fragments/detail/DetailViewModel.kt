package com.aditya.shopit.fragments.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG= "DetailViewModel"

class DetailViewModel(private val repository: ShopRepository) : ViewModel() {

    private val _product = MutableLiveData<Products>()
    val product: LiveData<Products>
        get() = _product

    private val _error = MutableLiveData<String?>(null)
    val error :LiveData<String?>
    get() = _error

    fun getProduct(id: Int) {
        viewModelScope.launch {
            try{
                _error.value = null
                val response = repository.getProduct(id)
                if (response.isSuccessful){
                    _product.value = response.body()
                }
                else{
                    _error.postValue(response.errorBody().toString())
                    Log.i(TAG, _error.toString())
                }
            }
            catch (e: Exception){
                _error.postValue(e.message)
            }
        }
    }

}