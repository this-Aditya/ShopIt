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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel : ViewModel() {
    private val TAG = "EditViewModel"
    private val _product: MutableLiveData<Products> = MutableLiveData()
    val product: LiveData<Products>
        get() = _product

    private val _stateStatus: MutableLiveData<States> = MutableLiveData()
    val stateStatus: LiveData<States>
        get() = _stateStatus

    fun putProduct(product: Products) {
        try {
            _stateStatus.value = States.READY
            viewModelScope.launch {
                RetrofitInstance.api.putProduct(product.id, product)
            }
            _stateStatus.value = States.SENT
        } catch (e: Exception) {
            _stateStatus.value = States.ERROR
            Log.i(TAG, "${e.message}")
        } }

    fun patchProduct(productId: Int, description: String, price: Float) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                Log.i(TAG, Thread.currentThread().name)
                withContext(Dispatchers.Main){
                    _stateStatus.value = States.READY}
                delay(500)
                val patchedProduct = PatchProduct(price, description)
                RetrofitInstance.api.patchProduct(productId, patchedProduct)
                withContext(Dispatchers.Main){
                    _stateStatus.value = States.SENT}            }
        } catch (e: Exception) {
            Log.i(TAG, "patchProduct: ${e.message}")
            _stateStatus.value = States.ERROR
        }
    }

    fun postProduct(product:Products) {
         try {
             viewModelScope.launch {
             _stateStatus.value = States.READY
             delay(500)
             RetrofitInstance.api.postProduct(product)
                 _stateStatus.value = States.SENT
         }}catch (e:Exception){
             Log.i(TAG, "${e.message}")
             _stateStatus.value = States.ERROR
         }
    }

    fun deletePost(productId:Int){
        try {
            viewModelScope.launch {
                    _stateStatus.value = States.READY
                    delay(500)
                    RetrofitInstance.api.deleteProduct(productId)
                    _stateStatus.value = States.SENT
            } }catch (e:Exception){
            Log.i(TAG, "${e.message}")
            _stateStatus.value = States.ERROR
            }
    }
}