package com.dimas.numbers.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FactDao {

    @Query("SELECT * FROM facts")
    fun getAllFacts(): LiveData<List<FactsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(factsEntity: FactsEntity)

}