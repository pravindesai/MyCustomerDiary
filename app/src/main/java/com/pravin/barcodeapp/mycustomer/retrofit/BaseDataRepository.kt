package com.pravin.barcodeapp.mycustomer.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.model.BaseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseDataRepository : RetrofitRepository() {
    private val baseDataApiEndPoints:BaseDataApiEndPoints
    private lateinit var baseGenderData:MutableLiveData<BaseData>
    private lateinit var baseStatusData:MutableLiveData<BaseData>
    private lateinit var baseTypeData:MutableLiveData<BaseData>

    init {
        baseDataApiEndPoints = retrofit.create(BaseDataApiEndPoints::class.java)
    }

    fun getGenederOptions():MutableLiveData<BaseData>{
        val call:Call<BaseData> = baseDataApiEndPoints.getGender()
        baseGenderData = MutableLiveData()
        call.enqueue(object: Callback<BaseData>{
            override fun onResponse(call: Call<BaseData>, response: Response<BaseData>) {
                if (response.isSuccessful){
                    baseGenderData.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<BaseData>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseGenderData
    }
    fun getStatusOptions():MutableLiveData<BaseData>{
        val call:Call<BaseData> = baseDataApiEndPoints.getStatus()
        baseStatusData = MutableLiveData()
        call.enqueue(object: Callback<BaseData>{
            override fun onResponse(call: Call<BaseData>, response: Response<BaseData>) {
                if (response.isSuccessful){
                    baseGenderData.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<BaseData>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseStatusData
    }
    fun getTypeOptions():MutableLiveData<BaseData>{
        val call:Call<BaseData> = baseDataApiEndPoints.getType()
        baseTypeData = MutableLiveData()
        call.enqueue(object: Callback<BaseData>{
            override fun onResponse(call: Call<BaseData>, response: Response<BaseData>) {
                if (response.isSuccessful){
                    baseGenderData.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<BaseData>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseTypeData
    }
}