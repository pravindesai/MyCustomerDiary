package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Status

@Dao
interface StatusDao {

    @Query("SELECT * FROM Status")
     fun getAll(): List<Status>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(vararg status: Status)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(objects: List<Status>)

    @Delete
     fun delete(status: Status)
}
