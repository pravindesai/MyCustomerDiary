package com.pravin.barcodeapp.mycustomer.dao

import androidx.room.*
import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Type

@Dao
interface TypeDao {

    @Query("SELECT * FROM Type")
    suspend fun getAll(): List<Type>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg type: Type)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objects: List<Type>)

    @Delete
     suspend fun delete(type: Type)
}
