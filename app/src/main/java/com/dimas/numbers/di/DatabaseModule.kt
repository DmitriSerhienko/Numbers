package com.dimas.numbers.di

import android.content.Context
import androidx.room.Room
import com.dimas.numbers.data.Constants.Companion.DATABASE_NAME
import com.dimas.numbers.data.db.FactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFactsDao(factsDatabase: FactDatabase) = factsDatabase.getArticleDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FactDatabase::class.java,
        DATABASE_NAME
    ).build()
}