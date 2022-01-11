package com.pravin.barcodeapp.mycustomer.Util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.database.RoomDb

open class BaseActivity : AppCompatActivity() {
    val TAG = "**"+this::class.java.simpleName

    lateinit var roomDb: RoomDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roomDb =  Room.databaseBuilder(
            applicationContext,
            RoomDb::class.java, Constants.DbName
        ).allowMainThreadQueries().build()

    }

}