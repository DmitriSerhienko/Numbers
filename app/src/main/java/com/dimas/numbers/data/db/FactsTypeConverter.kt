package com.dimas.numbers.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FactsTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun resultToString(number: FactItem):String {
        return gson.toJson(number)
    }

    @TypeConverter
    fun stringToResult(data: String): FactItem{
        val listType = object : TypeToken<FactItem>() {}.type
        return gson.fromJson(data,listType)
    }
}