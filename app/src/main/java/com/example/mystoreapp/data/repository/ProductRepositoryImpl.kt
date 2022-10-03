package com.example.mystoreapp.data.repository

import com.example.mystoreapp.data.api.ApiService
import com.example.mystoreapp.data.api.makeNetworkCall
import com.example.mystoreapp.data.db.dao.ProductDao
import com.example.mystoreapp.data.db.entities.ProductEntity
import com.example.mystoreapp.data.db.makeDatabaseCall
import com.example.mystoreapp.data.mappers.toEntity
import com.example.mystoreapp.data.mappers.toModel
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.utils.ResponseStatus
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val productDao: ProductDao
) : ProductRepository {

    //From Api
    override suspend fun getProductsFromApi(): ResponseStatus<MutableList<Product>> = makeNetworkCall {
        val response = api.getProducts()
        response.products.map { productDto ->
            productDto.toModel()
        }.toMutableList()
    }

    //From Database
    override suspend fun insertProductsIntoDatabase(products: MutableList<Product>) = makeNetworkCall {
        productDao.insertProducts(products.map { product -> product.toEntity() })
    }

    override suspend fun getProductsFromDatabase(): ResponseStatus<MutableList<Product>> = makeNetworkCall {
        productDao.getProducts().map { postEntity ->
            postEntity.toModel()
        }.toMutableList()
    }

    override suspend fun getProductById(id: Int): ResponseStatus<Product> = makeDatabaseCall {
        productDao.getProductById(id)
    }

}