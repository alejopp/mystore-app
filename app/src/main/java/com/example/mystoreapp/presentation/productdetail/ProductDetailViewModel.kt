package com.example.mystoreapp.presentation.productdetail

import androidx.lifecycle.ViewModel
import com.example.mystoreapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
}