package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Gender

@Dao
interface GenderDao {

    @Query("SELECT * FROM gender")
     suspend fun getAll(): List<Gender>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg gender: Gender)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objects: List<Gender>)

    @Delete
    suspend fun delete(gender: Gender)
}
