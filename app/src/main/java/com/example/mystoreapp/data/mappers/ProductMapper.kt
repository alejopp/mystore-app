package com.example.mystoreapp.data.mappers

import com.example.mystoreapp.data.db.entities.ProductEntity
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.data.models.dto.ProductDto

fun ProductDto.toModel() = Product(
    id,
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

fun ProductEntity.toModel() = Product(
    id,
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

fun Product.toEntity() = ProductEntity(
    id,
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