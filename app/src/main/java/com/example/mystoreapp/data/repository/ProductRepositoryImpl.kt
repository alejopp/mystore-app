package com.example.mystoreapp.data.repository

import com.example.mystoreapp.data.api.makeNetworkCall
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.utils.ResponseStatus

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getProductList(): ResponseStatus<List<Product>> = makeNetworkCall {
        emptyList()
    }
}