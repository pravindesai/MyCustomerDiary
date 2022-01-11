package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Status
import com.pravin.barcodeapp.mycustomer.model.Type
import retrofit2.Call
import retrofit2.http.GET

interface BaseDataApiEndPoints {

    @GET("basedata/gender")
    fun getGender(): Call<List<String>>

    @GET("basedata/status")
    fun getStatus(): Call<List<String>>

    @GET("basedata/type")
    fun getType(): Call<List<String>>

}