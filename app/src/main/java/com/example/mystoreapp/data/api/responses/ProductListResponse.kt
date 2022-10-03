package com.example.mystoreapp.data.api.responses

import com.example.mystoreapp.data.models.dto.ProductDto

data class ProductListResponse (
    val products: List<ProductDto>
)