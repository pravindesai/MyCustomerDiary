package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.model.BaseData
import retrofit2.Call
import retrofit2.http.GET

interface BaseDataApiEndPoints {

    @GET("basedata/gender")
    fun getGender(): Call<BaseData>

    @GET("basedata/status")
    fun getStatus(): Call<BaseData>

    @GET("basedata/type")
    fun getType(): Call<BaseData>

}