package com.example.mystoreapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mystoreapp.data.db.dao.ProductDao
import com.example.mystoreapp.data.db.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}