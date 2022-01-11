package com.pravin.barcodeapp.mycustomer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gender(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var gender: String
)
