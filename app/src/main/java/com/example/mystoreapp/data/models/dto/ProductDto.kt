package com.example.mystoreapp.data.models.dto

import com.example.mystoreapp.data.models.Product

data class ProductDto(
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)