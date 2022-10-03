package com.example.mystoreapp.data.api

import com.example.mystoreapp.data.api.responses.ProductListResponse
import com.example.mystoreapp.utils.PRODUCTS
import retrofit2.http.GET

interface ApiService {

    @GET(PRODUCTS)
    suspend fun getProducts(): ProductListResponse

}