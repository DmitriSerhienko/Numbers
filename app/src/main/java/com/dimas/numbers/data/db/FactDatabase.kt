package com.dimas.numbers.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FactsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FactsTypeConverter::class)
abstract class FactDatabase : RoomDatabase() {

    abstract fun getArticleDao(): FactDao

}