package com.cursokotlin.retrofitkotlinexample.data.mapper

import com.cursokotlin.retrofitkotlinexample.data.services.dogsapi.response.DogsResponse
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel

fun DogsResponse.transformToDomain(): DogsModel {
    return DogsModel(
        status.orEmpty(),
        images.orEmpty()
    )
}