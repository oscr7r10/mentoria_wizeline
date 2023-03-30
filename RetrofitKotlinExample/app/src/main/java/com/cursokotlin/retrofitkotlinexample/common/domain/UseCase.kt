package com.cursokotlin.retrofitkotlinexample.common.domain

import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    fun execute(params: Params? = null): Flow<ResultAPI<T>>
}