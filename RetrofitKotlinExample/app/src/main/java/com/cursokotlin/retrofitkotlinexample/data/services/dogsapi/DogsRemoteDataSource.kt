package com.cursokotlin.retrofitkotlinexample.data.services.dogsapi

import com.cursokotlin.retrofitkotlinexample.data.mapper.transformToDomain
import com.cursokotlin.retrofitkotlinexample.data.repository.datasource.DogsDataSource
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import javax.inject.Inject

class DogsRemoteDataSource @Inject constructor(
    private val apiService: APIService
) : DogsDataSource {

    override suspend fun getDogsByName(dogName: String): ResultAPI<DogsModel> {
        return try {
            val response = apiService.getCharacterByName(dogName)
            response.body()?.let {
                if (response.isSuccessful) {
                    if (it.status.orEmpty() == "success") ResultAPI.OnSuccess(it.transformToDomain())
                    else ResultAPI.OnFailure(Exception())
                } else ResultAPI.OnFailure(Exception())
            } ?: ResultAPI.OnFailure(Exception())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }
}