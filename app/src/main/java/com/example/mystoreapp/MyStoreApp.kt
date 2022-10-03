package com.example.mystoreapp

import android.app.Application
import androidx.room.Room
import com.example.mystoreapp.data.db.ProductDatabase
import com.example.mystoreapp.utils.PRODUCT_DATABASE_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyStoreApp: Application()