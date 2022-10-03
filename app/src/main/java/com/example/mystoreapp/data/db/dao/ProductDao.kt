package com.example.mystoreapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mystoreapp.data.db.entities.ProductEntity
import com.example.mystoreapp.data.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM product_table")
    suspend fun getProducts(): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE id = :id")
    suspend fun getProductById(id: Int): Product

}