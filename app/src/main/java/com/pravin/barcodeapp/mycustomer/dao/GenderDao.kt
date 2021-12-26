package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*

@Dao
interface GenderDao {
    @Query("SELECT * FROM gender")
    fun getAll(): List<String>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gender: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objects: ArrayList<String>)
    @Delete
    fun delete(gender: String)
}
