package com.pravin.barcodeapp.mycustomer.model


import com.google.gson.annotations.SerializedName

data class Admin(
    @SerializedName("address")
    var address: Address,
    @SerializedName("adminUid")
    var adminUid: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("phoneNumber")
    var phoneNumber: String,
    @SerializedName("serviceName")
    var serviceName: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type: String
)