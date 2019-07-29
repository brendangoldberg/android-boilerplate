package com.example.utils

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

fun <T> error() = Response.error<T>(
    400,
    ResponseBody.create(MediaType.get("application/json"), "someString")
)