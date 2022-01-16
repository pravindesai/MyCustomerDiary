package com.pravin.barcodeapp.mycustomer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pravin.barcodeapp.mycustomer.dao.GenderDao
import com.pravin.barcodeapp.mycustomer.dao.StatusDao
import com.pravin.barcodeapp.mycustomer.dao.TypeDao
import com.pravin.barcodeapp.mycustomer.model.*
import javax.inject.Inject

@Database(entities = [Gender::class, Status::class,   Type::class,
                      Address::class, Admin::class,   Batch::class,
                      Customer::class, Staff::class], version = 1)
@TypeConverters(AddressTypeConverter::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun genderDao(): GenderDao
    abstract fun statusDao(): StatusDao
    abstract fun typeDao():TypeDao
}