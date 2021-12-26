package com.pravin.barcodeapp.mycustomer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pravin.barcodeapp.mycustomer.dao.GenderDao
import com.pravin.barcodeapp.mycustomer.model.Gender

@Database(entities = [Gender::class], version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun genderDao(): GenderDao
}