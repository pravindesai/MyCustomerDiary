package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Customer
import com.pravin.barcodeapp.mycustomer.model.Gender

@Dao
interface CustomerDao {
    @Query("SELECT * FROM Customer")
    fun getAll(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gender: Customer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objects: List<Customer>)

    @Query("SELECT * FROM Customer where adminUid = :adminUid ")
    fun selectByAdminUid(adminUid:String)

    @Delete
    fun delete(gender: Customer)
}