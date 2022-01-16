package com.pravin.barcodeapp.mycustomer.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Status
import com.pravin.barcodeapp.mycustomer.model.Type
import com.pravin.barcodeapp.mycustomer.service.BaseDataApiEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BaseDataRepository @Inject constructor(): RetrofitRepository() {

    @Inject
    lateinit var baseDataApiEndPoints: BaseDataApiEndPoints

    private lateinit var baseGenderData:MutableLiveData<List<Gender>>
    private lateinit var baseStatusData:MutableLiveData<List<Status>>
    private lateinit var baseTypeData:MutableLiveData<List<Type>>


    fun getGenederOptions():MutableLiveData<List<Gender>>{
        val call:Call<List<String>> = baseDataApiEndPoints.getGender()
        baseGenderData = MutableLiveData()
        call.enqueue(object: Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                        val genderList = mutableListOf<Gender>()
                        response.body()?.forEach {
                            genderList.add(Gender(0,it))
                        }
                        baseGenderData.postValue(genderList)
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseGenderData
    }
    fun getStatusOptions():MutableLiveData<List<Status>>{
        val call:Call<List<String>> = baseDataApiEndPoints.getStatus()
        baseStatusData = MutableLiveData()
        call.enqueue(object: Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                    val statusList = mutableListOf<Status>()
                    response.body()?.forEach {
                        statusList.add(Status(0,it))
                    }
                    baseStatusData.postValue(statusList)
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseStatusData
    }
    fun getTypeOptions():MutableLiveData<List<Type>>{
        val call:Call<List<String>> = baseDataApiEndPoints.getType()
        baseTypeData = MutableLiveData()
        call.enqueue(object: Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                    val typeList = mutableListOf<Type>()
                    response.body()?.forEach {
                        typeList.add(Type(0,it))
                    }
                    baseTypeData.postValue(typeList)
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e(TAG, "Failed ==> "+t.localizedMessage )
            }
        })
        return baseTypeData
    }
}