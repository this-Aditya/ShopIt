package com.aditya.shopit.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.aditya.shopit.repository.ShopRepository

class ListViewModelFactory(private val repository: ShopRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ListViewModel(repository) as T
    }
}