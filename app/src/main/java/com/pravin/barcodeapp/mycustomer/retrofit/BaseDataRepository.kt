package com.pravin.barcodeapp.mycustomer.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Status
import com.pravin.barcodeapp.mycustomer.model.Type
import com.pravin.barcodeapp.mycustomer.service.BaseDataApiEndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BaseDataRepository @Inject constructor(): RetrofitRepository() {

    @Inject
    lateinit var baseDataApiEndPoints: BaseDataApiEndPoints


    fun getGenederOptions() :Flow<List<String>>{
        return flow {
            val result = baseDataApiEndPoints.getGender()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    fun getStatusOptions()  :Flow<List<String>>{
        return flow {
            val result = baseDataApiEndPoints.getStatus()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    fun getTypeOptions()    :Flow<List<String>>{
        return flow {
            val result = baseDataApiEndPoints.getType()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}