package com.example.mystoreapp.data.repository

import com.example.mystoreapp.data.api.ApiService
import com.example.mystoreapp.data.api.makeNetworkCall
import com.example.mystoreapp.data.mappers.toModel
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.utils.ResponseStatus
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ApiService): ProductRepository {

    override suspend fun getProductList(): ResponseStatus<MutableList<Product>> = makeNetworkCall {
        val response = api.getProducts()
        response.products.map {  productDto ->
            productDto.toModel()
        }.toMutableList()
    }

}