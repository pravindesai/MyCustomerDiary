package com.pravin.barcodeapp.mycustomer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Status(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var status: String
)
