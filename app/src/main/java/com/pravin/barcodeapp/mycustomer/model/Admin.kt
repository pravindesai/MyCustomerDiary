package com.pravin.barcodeapp.mycustomer.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,
    @SerializedName("address")
    var address: Address,
    @SerializedName("adminUid")
    var adminUid: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("phoneNumber")
    var phoneNumber: String,
    @SerializedName("serviceName")
    var serviceName: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type: String
)