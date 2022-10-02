package com.example.mystoreapp.data.repository

import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.utils.ResponseStatus

interface ProductRepository {

    suspend fun getProductList(): ResponseStatus<MutableList<Product>>

}