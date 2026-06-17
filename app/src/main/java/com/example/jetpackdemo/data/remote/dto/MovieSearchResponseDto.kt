package com.example.jetpackdemo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponseDto(
    @SerialName("Search") val movies: List<MovieDto>? = null,
    @SerialName("totalResults") val totalResults: String? = null,
    @SerialName("Error") val error: String? = null,
    @SerialName("Response") val response: String
)