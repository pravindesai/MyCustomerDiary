package com.pravin.barcodeapp.mycustomer.application

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CustomApplication: Application() {
    val TAG:String = "**"+this::class.java.simpleName
    companion object{
        lateinit var appContext:Context
        private fun setContext(context: Context){
            appContext = context
        }
    }
    override fun onCreate() {
        super.onCreate()
        setContext(this)
        Log.e(TAG, "APP INSTANTIATED " )
    }
}