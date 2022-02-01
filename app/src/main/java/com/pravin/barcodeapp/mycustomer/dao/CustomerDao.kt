package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Customer
import com.pravin.barcodeapp.mycustomer.model.Gender

@Dao
interface CustomerDao {
    @Query("SELECT * FROM Customer")
    suspend fun getAll(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg gender: Customer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objects: List<Customer>)

    @Query("SELECT * FROM Customer where adminUid = :adminUid ")
    suspend fun selectByAdminUid(adminUid:String):List<Customer>

    @Delete
    suspend fun delete(gender: Customer)
}