package com.example.mystoreapp.data.repository

import com.example.mystoreapp.data.db.ProductDatabase
import com.example.mystoreapp.data.db.entities.ProductEntity
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.utils.ResponseStatus

interface ProductRepository {

    suspend fun getProductsFromApi(): ResponseStatus<MutableList<Product>>

    //Database
    suspend fun insertProductsIntoDatabase(products: MutableList<Product>): ResponseStatus<Unit>
    suspend fun getProductsFromDatabase(): ResponseStatus<MutableList<Product>>

}