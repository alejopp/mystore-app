package com.example.mystoreapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.mystoreapp.data.db.ProductDatabase
import com.example.mystoreapp.utils.PRODUCT_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProductDatabase::class.java, PRODUCT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductDao(db: ProductDatabase) = db.getProductDao()

}