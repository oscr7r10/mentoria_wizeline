package com.cursokotlin.retrofitkotlinexample.data.repository

import com.cursokotlin.retrofitkotlinexample.data.repository.datasource.DogsDataSource
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.injection.DogsRepositoryRemote
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsRepository @Inject constructor(
    @DogsRepositoryRemote val remoteDataSource: DogsDataSource
) {
    fun getDogByName(dogName: String): Flow<ResultAPI<DogsModel>> = flow {
        emit(remoteDataSource.getDogsByName(dogName))
    }
}