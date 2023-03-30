package com.cursokotlin.retrofitkotlinexample.domain.usecase

import com.cursokotlin.retrofitkotlinexample.common.domain.UseCase
import com.cursokotlin.retrofitkotlinexample.data.repository.DogsRepository
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDogsByNameUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) : UseCase<SearchDogsByNameUseCase.Params, DogsModel> {
    override fun execute(params: Params?): Flow<ResultAPI<DogsModel>> {
        return params?.let {
            dogsRepository.getDogByName(it.dogName)
        } ?: dogsRepository.getDogByName("")
    }

    data class Params(
        val dogName: String
    )
}