package com.pravin.barcodeapp.mycustomer.service

import retrofit2.Call
import retrofit2.http.GET

interface BaseDataApiEndPoints {

    @GET("basedata/gender")
    suspend fun getGender(): List<String>

    @GET("basedata/status")
    suspend fun getStatus(): List<String>

    @GET("basedata/type")
    suspend fun getType(): List<String>

}