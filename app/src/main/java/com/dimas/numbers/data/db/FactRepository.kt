package com.dimas.numbers.data.db

import androidx.lifecycle.LiveData
import javax.inject.Inject

class FactRepository @Inject constructor(private val numbersDao: FactDao) {

    val getAllFacts: LiveData<List<FactsEntity>> = numbersDao.getAllFacts()

    suspend fun insertNumber(factsEntity: FactsEntity) {
        numbersDao.insert(factsEntity)
    }

}