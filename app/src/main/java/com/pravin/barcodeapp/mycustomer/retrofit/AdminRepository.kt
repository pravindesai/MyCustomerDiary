package com.pravin.barcodeapp.mycustomer.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Batch
import com.pravin.barcodeapp.mycustomer.model.Staff
import com.pravin.barcodeapp.mycustomer.service.AdminEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AdminRepository @Inject constructor(): RetrofitRepository() {
    @Inject
    lateinit var adminEndpoints: AdminEndpoints

    fun getAdmin():MutableLiveData<List<Admin>>{
        val adminList:MutableLiveData<List<Admin>> = MutableLiveData()
        val call:Call<List<Admin>> = adminEndpoints.getAdmin()

        call.enqueue(object:Callback<List<Admin>>{
            override fun onResponse(call: Call<List<Admin>>, response: Response<List<Admin>>) {
                if (response.isSuccessful){
                    adminList.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                    Log.e(TAG, "onResponse: Size -->"+(response.body()?.size) )
                }
            }
            override fun onFailure(call: Call<List<Admin>>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return adminList
    }

    fun getAdmin(admin_uid:String):MutableLiveData<Admin>{
        val admin = MutableLiveData<Admin>()
        val call:Call<Admin> = adminEndpoints.getAdmin(admin_uid)
        call.enqueue(object:Callback<Admin>{
            override fun onResponse(call: Call<Admin>, response: Response<Admin>) {
                if (response.isSuccessful){
                    admin.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }else{
                    Log.e(TAG, "onResponse: Error "+response )
                }
            }
            override fun onFailure(call: Call<Admin>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return admin
    }

    fun getStaff(admin_uid:String, phone:String):MutableLiveData<Staff>{
        val staff = MutableLiveData<Staff>()
        val call:Call<Staff> = adminEndpoints.getDefaultStaff(admin_uid, phone)
        call.enqueue(object:Callback<Staff>{
            override fun onResponse(call: Call<Staff>, response: Response<Staff>) {
                if (response.isSuccessful){
                    staff.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }else{
                    Log.e(TAG, "onResponse: Error "+response )
                }
            }
            override fun onFailure(call: Call<Staff>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })

        return staff
    }

    fun postAdmin(admin: Admin):MutableLiveData<Staff>{
        val uploadedAdmin = MutableLiveData<Staff>()
        val call:Call<Staff> = adminEndpoints.postAdmin(admin)
        call.enqueue(object:Callback<Staff>{
            override fun onResponse(call: Call<Staff>, response: Response<Staff>) {
                if (response.isSuccessful){
                    uploadedAdmin.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }else{
                    Log.e(TAG, "onResponse: Error "+response )
                }
            }
            override fun onFailure(call: Call<Staff>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return uploadedAdmin
    }

    fun putAdmin(admin_uid: String, admin: Admin):MutableLiveData<Staff>{

        val updatedAdmin = MutableLiveData<Staff>()
        val call:Call<Staff> = adminEndpoints.putAdmin(admin_uid, admin)
        call.enqueue(object:Callback<Staff>{
            override fun onResponse(call: Call<Staff>, response: Response<Staff>) {
                if (response.isSuccessful){
                    updatedAdmin.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }
            }

            override fun onFailure(call: Call<Staff>, t: Throwable) {
                Log.e(TAG, "onFailure: FAILED" )
            }

        })

        return updatedAdmin;
    }

    fun getBatchForAdmin(admin_uid: String):MutableLiveData<List<Batch>>{
        val batchList:MutableLiveData<List<Batch>> = MutableLiveData()

        val call:Call<List<Batch>> = adminEndpoints.getBatchForAdmin(admin_uid)
        call.enqueue(object : Callback<List<Batch>>{
            override fun onResponse(call: Call<List<Batch>>, response: Response<List<Batch>>) {
                if (response.isSuccessful){
                    batchList.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )
                }else{
                    Log.e(TAG, "onResponse: Failed" )
                }
            }
            override fun onFailure(call: Call<List<Batch>>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return batchList
    }

    fun postBatchForAdmin(admin_uid: String, batch: Batch):MutableLiveData<Batch>{
        val updatedBatch:MutableLiveData<Batch> = MutableLiveData()

        val call:Call<Batch> = adminEndpoints.postBatchForAdmin(admin_uid, batch)
        call.enqueue(object : Callback<Batch>{
            override fun onResponse(call: Call<Batch>, response: Response<Batch>) {
                if (response.isSuccessful){
                    updatedBatch.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )

                }else{
                    Log.e(TAG, "onResponse: Failed" )
                }
            }
            override fun onFailure(call: Call<Batch>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return updatedBatch
    }

    fun putBatchForAdmin(admin_uid: String, bath_id:String,batch: Batch):MutableLiveData<Batch>{
        val updatedBatch:MutableLiveData<Batch> = MutableLiveData()

        val call:Call<Batch> = adminEndpoints.putBatchForAdmin(admin_uid,bath_id, batch)
        call.enqueue(object : Callback<Batch>{
            override fun onResponse(call: Call<Batch>, response: Response<Batch>) {
                if (response.isSuccessful){
                    updatedBatch.postValue(response.body())
                    Log.e(TAG, "onResponse: "+response.body() )

                }else{
                    Log.e(TAG, "onResponse: Failed" )
                }
            }
            override fun onFailure(call: Call<Batch>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }
        })
        return updatedBatch
    }

    fun deleteBatchForAdmin(admin_uid: String, batch_id:Int){
        val call:Call<Void> = adminEndpoints.deleteBatchForAdmin(admin_uid, batch_id)
        call.enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    Log.e(TAG, "onResponse: Delete sucessful" )
                }else{
                    Log.e(TAG, "onResponse: Error" )
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.localizedMessage )
            }

        })
    }

}