package com.pravin.barcodeapp.mycustomer.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Batch(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("adminUid")
    var adminUid: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("duration_each_day")
    var durationEachDay: Int,
    @SerializedName("end_Date")
    var endDate: Int,
    @SerializedName("fee")
    var fee: Int,
    @SerializedName("staffId")
    var staffId: Int,
    @SerializedName("start_Date")
    var startDate: Int,
    @SerializedName("type")
    var type: String
)