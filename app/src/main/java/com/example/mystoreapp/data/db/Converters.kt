package com.example.mystoreapp.data.db

import androidx.room.TypeConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Converters {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split("&").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = "&")
    }
}


