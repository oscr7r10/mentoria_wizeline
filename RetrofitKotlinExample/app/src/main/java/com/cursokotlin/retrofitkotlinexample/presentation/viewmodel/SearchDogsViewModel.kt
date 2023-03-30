package com.cursokotlin.retrofitkotlinexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.domain.usecase.SearchDogsByNameUseCase
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import com.cursokotlin.retrofitkotlinexample.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDogsViewModel @Inject constructor(
    private val searchDogsByNameUseCase: SearchDogsByNameUseCase
) : ViewModel() {

    private val _searchDogService = SingleLiveEvent<ResultAPI<DogsModel>>()
    val searchDogService: LiveData<ResultAPI<DogsModel>> get() = _searchDogService

    fun searchDogByName(dogName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchDogsByNameUseCase.execute(SearchDogsByNameUseCase.Params(dogName)).collect {
                _searchDogService.postValue(it)
            }
        }
    }
}