package com.example.mystoreapp.data.mappers

import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.data.models.dto.ProductDto

fun ProductDto.toModel() = Product(
    title,
    description,
    price,
    discountPercentage,
    rating,
    stock,
    brand,
    category,
    thumbnail,
    images
)