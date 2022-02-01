package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Status

@Dao
interface StatusDao {

    @Query("SELECT * FROM Status")
     suspend fun getAll(): List<Status>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(vararg status: Status)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(objects: List<Status>)

    @Delete
     suspend fun delete(status: Status)
}
