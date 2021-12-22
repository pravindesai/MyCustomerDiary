package com.pravin.barcodeapp.mycustomer.Util

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.util.Log

open class RetrofitRepository {
    val TAG = "**"+this::class.java.simpleName
    var retrofit: Retrofit

    init {
        this.retrofit =
                        Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
             .build()
          Log.e(TAG, "retrofit Instantiated" )
    }
}