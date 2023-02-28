package com.dimas.numbers.data.network

import com.dimas.numbers.data.db.FactItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/random/math?json")
    suspend fun getRandomFact(): Response<FactItem>

    @GET("{searchQuery}?json")
    suspend fun getFact(@Path("searchQuery") searchQuery: Long): Response<FactItem>

}
