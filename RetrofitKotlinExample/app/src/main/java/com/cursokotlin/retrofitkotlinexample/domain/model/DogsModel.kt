package com.cursokotlin.retrofitkotlinexample.domain.model

import com.google.gson.annotations.SerializedName

data class DogsModel(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)