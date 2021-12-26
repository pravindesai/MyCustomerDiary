package com.pravin.barcodeapp.mycustomer.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Address(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("area")
    var area: String,
    @SerializedName("city")
    var city: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("landmark")
    var landmark: String,
    @SerializedName("pincode")
    var pincode: Int,
    @SerializedName("state")
    var state: String
)