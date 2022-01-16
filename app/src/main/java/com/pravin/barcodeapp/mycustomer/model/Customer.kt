package com.pravin.barcodeapp.mycustomer.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.pravin.barcodeapp.mycustomer.database.AddressTypeConverter

@Entity
data class Customer(
    @SerializedName("id")
    var id: Int,

    @SerializedName("adminUid")
    @PrimaryKey
    var adminUid: String,

    @TypeConverters(AddressTypeConverter::class)
    @SerializedName("address")
    var address: Address,
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