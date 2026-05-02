package com.example.jetpackdemo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("Title") val title : String? = null,
    @SerialName("Year") val year : String? = null,
    @SerialName("imdbID") val imdbID : String? = null,
    @SerialName("Type") val type : String? = null,
    @SerialName("Poster") val poster : String? = null
)