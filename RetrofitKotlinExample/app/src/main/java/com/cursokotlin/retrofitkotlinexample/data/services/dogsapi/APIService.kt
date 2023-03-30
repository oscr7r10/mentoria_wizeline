package com.cursokotlin.retrofitkotlinexample.data.services.dogsapi

import com.cursokotlin.retrofitkotlinexample.data.services.dogsapi.response.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("breed/{dogName}/images")
    suspend fun getCharacterByName(@Path("dogName") dogName: String): Response<DogsResponse>
}