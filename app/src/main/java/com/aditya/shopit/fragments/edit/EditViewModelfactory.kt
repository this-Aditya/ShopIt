package com.aditya.shopit.fragments.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.shopit.repository.ShopRepository

class EditViewModelfactory(private val repository: ShopRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditViewModel(repository) as T
    }
}