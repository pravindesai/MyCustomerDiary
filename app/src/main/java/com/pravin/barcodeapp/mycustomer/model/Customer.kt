package com.pravin.barcodeapp.mycustomer.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Customer(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("address")
    var address: Address,
    @SerializedName("adminUid")
    var adminUid: String,
    @SerializedName("batchId")
    var batchId: Int,
    @SerializedName("due_date")
    var dueDate: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("joining_date")
    var joiningDate: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("staffId")
    var staffId: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type: String
)