package com.example.mystoreapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystoreapp.data.di.RetrofitProvider
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    fun getProductList(){
        viewModelScope.launch {
            val response = RetrofitProvider.retrofit.getProducts()
            val a = productList
        }
    }

}