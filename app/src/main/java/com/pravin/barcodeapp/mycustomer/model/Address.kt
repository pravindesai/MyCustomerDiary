package com.pravin.barcodeapp.mycustomer.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("area")
    var area: String,
    @SerializedName("city")
    var city: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("landmark")
    var landmark: String,
    @SerializedName("pincode")
    var pincode: Int,
    @SerializedName("state")
    var state: String
)