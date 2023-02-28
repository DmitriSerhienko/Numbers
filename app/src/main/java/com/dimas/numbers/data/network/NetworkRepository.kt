package com.dimas.numbers.data.network

import com.dimas.numbers.data.db.FactItem
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val factsApi: ApiService) {

    suspend fun getRandomFact(): Response<FactItem> {
        return factsApi.getRandomFact()

    }

    suspend fun getInputFact(inputFact: Long): Response<FactItem> {
        return factsApi.getFact(inputFact)
    }
}