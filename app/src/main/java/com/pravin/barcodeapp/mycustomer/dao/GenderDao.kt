package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Gender

@Dao
interface GenderDao {

    @Query("SELECT * FROM gender")
     fun getAll(): List<Gender>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(vararg gender: Gender)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(objects: List<Gender>)

    @Delete
     fun delete(gender: Gender)
}