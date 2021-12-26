package com.pravin.barcodeapp.mycustomer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class Gender(
    @PrimaryKey(autoGenerate = true)
    public  var id: Int,
    public  var gender: String
)
