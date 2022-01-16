package com.pravin.barcodeapp.mycustomer.Util

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.util.Log
import javax.inject.Inject

open class RetrofitRepository @Inject constructor() {
    val TAG = "**"+this::class.java.simpleName

    @Inject
    lateinit var retrofit: Retrofit

}