package com.pravin.barcodeapp.mycustomer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pravin.barcodeapp.mycustomer.dao.GenderDao
import com.pravin.barcodeapp.mycustomer.dao.StatusDao
import com.pravin.barcodeapp.mycustomer.dao.TypeDao
import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Status
import com.pravin.barcodeapp.mycustomer.model.Type

@Database(entities = [Gender::class, Status::class, Type::class], version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun genderDao(): GenderDao
    abstract fun statusDao(): StatusDao
    abstract fun typeDao():TypeDao
}