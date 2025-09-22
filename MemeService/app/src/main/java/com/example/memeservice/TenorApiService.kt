package com.example.memeservice



import com.example.memeservice.TenorResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TenorApiService {
    @GET("v2/search")
    suspend fun searchGifs(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("limit") limit: Int = 20
    ): Response<TenorResponse>
}
