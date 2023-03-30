package com.cursokotlin.retrofitkotlinexample.data.repository.datasource

import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI

interface DogsDataSource {
    suspend fun getDogsByName(dogName: String): ResultAPI<DogsModel>
}