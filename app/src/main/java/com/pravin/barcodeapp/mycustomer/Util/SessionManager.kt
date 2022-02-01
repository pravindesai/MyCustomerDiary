package com.pravin.barcodeapp.mycustomer.Util

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.pravin.barcodeapp.mycustomer.application.CustomApplication
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Customer
import com.pravin.barcodeapp.mycustomer.model.Staff
import java.lang.Exception

object SessionManager {
    private val TAG: String = "**"+this::class.java.simpleName
    val PRIVATE_MODE = 0
    val gson:Gson

    val sharedPref:SharedPreferences
    private val tempSharedPref:SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPref = CustomApplication.appContext.getSharedPreferences(Constants.KEY_SHARED_PREF_FILE, PRIVATE_MODE)
        tempSharedPref = CustomApplication.appContext.getSharedPreferences(Constants.KEY_TEMP_SHARED_PREF_FILE, PRIVATE_MODE)
        editor = sharedPref.edit()
        gson = Gson()
    }

    fun setDataToSync(value:Boolean){
        editor.putBoolean(Constants.SYNC_DATA, value)
        editor.commit()
    }
    fun isDataToSync():Boolean = sharedPref.getBoolean(Constants.SYNC_DATA, false)


    fun saveBoolean(key:String, value:Boolean){
        editor.putBoolean(key, value)
        editor.commit()
    }
    fun getBoolean(key: String):Boolean = sharedPref.getBoolean(key, "".toBoolean())

    fun saveInt(key:String, value:Int){
        editor.putInt(key, value)
        editor.commit()
    }
    fun getInt(key: String): Int = sharedPref.getInt(key, "".toInt())


    fun saveFloat(key:String, value:Float){
        editor.putFloat(key, value)
        editor.commit()
    }
    fun getFloat(key: String): Float = sharedPref.getFloat(key, "".toFloat())


    fun saveLong(key:String, value:Long){
        editor.putLong(key, value)
        editor.commit()
    }
    fun getLong(key: String): Long = sharedPref.getLong(key, "".toLong())


    fun saveString(key:String, value:String){
        editor.putString(key, value)
        editor.commit()
    }
    fun getString(key: String): String? = sharedPref.getString(key, "")

    fun delete(key: String){
        editor.remove(key)
        editor.commit()
    }

    fun saveAdmin(key: String, admin: Admin){
        val serializedAdmin:String = gson.toJson(admin)
        editor.putString(key, serializedAdmin)
        editor.apply()
    }

    fun getAdmin(key: String):Admin{
        val serializedAdmin:String? = sharedPref.getString(key,"")
        val deserializedAdmin:Admin = gson.fromJson(serializedAdmin, Admin::class.java)
        return deserializedAdmin
    }

    fun saveStaff(key: String, staff: Staff){
        val serializedStaff:String = gson.toJson(staff)
        editor.putString(key, serializedStaff)
        editor.apply()
    }

    fun getStaff(key: String):Staff{
        val serializedStaff:String? = sharedPref.getString(key,"")
        val deserializedStaff:Staff = gson.fromJson(serializedStaff, Staff::class.java)
        return deserializedStaff
    }

    fun saveCustomer(key: String, customer: Customer){
        val serializedCustomer:String = gson.toJson(customer)
        editor.putString(key, serializedCustomer)
        editor.apply()
    }

    fun getCustomer(key: String):Customer{
        val serializedCustomer:String? = sharedPref.getString(key,"")
        val deserializedCustomer:Customer = gson.fromJson(serializedCustomer, Customer::class.java)
        return deserializedCustomer
    }


    fun clearSession():Boolean{
        try {
            editor.clear()
            editor.apply()
            return true
        }catch (e:Exception){
            Log.e(TAG, "clearSession: FAILED --> "+e.localizedMessage)
            editor.commit()
            return false
        }
    }



}