package com.example.jetpackdemo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    @SerialName("Search") val movies : List<Movie>? = null,
    @SerialName("totalResults") val totalResults : String? = null,
    @SerialName("Error") val error: String? = null,
    @SerialName("Response") val response : String
)
