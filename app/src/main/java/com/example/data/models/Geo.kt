package com.example.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geo(
    @Json(name = "lat") val latitude: String,
    @Json(name = "lng") val longitude: String
)