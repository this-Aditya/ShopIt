package com.aditya.shopit.fragments.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopit.States
import com.aditya.shopit.api.RetrofitInstance
import com.aditya.shopit.models.PatchProduct
import com.aditya.shopit.models.Products
import com.aditya.shopit.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel(private val repository: ShopRepository) : ViewModel() {
    private val TAG = "EditViewModel"
    private val _product: MutableLiveData<Products> = MutableLiveData()
    val product: LiveData<Products>
        get() = _product

    private val _stateStatus: MutableLiveData<States> = MutableLiveData()
    val stateStatus: LiveData<States>
        get() = _stateStatus

    fun putProduct(product: Products) {

        _stateStatus.value = States.READY
        viewModelScope.launch {
            try {
                repository.putProduct(product.id, product)
            } catch (e: Exception) {
                _stateStatus.value = States.ERROR
                Log.i(TAG, "${e.message}")
            }
        }
        _stateStatus.value = States.SENT
    }

    fun patchProduct(productId: Int, description: String, price: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i(TAG, Thread.currentThread().name)
                withContext(Dispatchers.Main){
                    _stateStatus.value = States.READY
                }
                delay(500)
                val patchedProduct = PatchProduct(price, description)
                repository.patchProduct(productId, patchedProduct)
                withContext(Dispatchers.Main){
                    _stateStatus.value = States.SENT
                }
            } catch (e: Exception) {
                Log.i(TAG, "patchProduct: ${e.message}")
                withContext(Dispatchers.Main){
                _stateStatus.value = States.ERROR}
            }
        }
    }


    fun postProduct(product:Products) {
             viewModelScope.launch {
                 try {
                     _stateStatus.value = States.READY
             delay(500)
             repository.postProduct(product)
                 _stateStatus.value = States.SENT
    } catch (e:Exception){
            Log.i(TAG, "${e.message}")
            _stateStatus.value = States.ERROR
        }}}

    fun deletePost(productId:Int){
            viewModelScope.launch {
                try {
                    _stateStatus.value = States.READY
                    delay(500)
                    repository.deleteProduct(productId)
                    _stateStatus.value = States.SENT
             }catch (e:Exception) {
                    Log.i(TAG, "${e.message}")
                    _stateStatus.value = States.ERROR
                }}
    }
}
