package com.pravin.barcodeapp.mycustomer.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pravin.barcodeapp.mycustomer.model.Address


class AddressTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun addressToString(address: Address): String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun stringToaddress(data: String): Address {
        val address = object : TypeToken<Address>() {
        }.type
        return gson.fromJson(data, address)
    }
}
