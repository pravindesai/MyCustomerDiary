package com.pravin.barcodeapp.mycustomer.Util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    val TAG = "**"+this::class.java.simpleName

    @Inject
    lateinit var roomDb: RoomDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}