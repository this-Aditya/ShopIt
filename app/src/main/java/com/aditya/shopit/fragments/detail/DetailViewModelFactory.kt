package com.aditya.shopit.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.shopit.repository.ShopRepository

class DetailViewModelFactory(private val repository: ShopRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}