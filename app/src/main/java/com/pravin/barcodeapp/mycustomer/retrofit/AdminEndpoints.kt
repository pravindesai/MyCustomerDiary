package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Batch
import com.pravin.barcodeapp.mycustomer.model.Staff
import retrofit2.Call
import retrofit2.http.*

interface AdminEndpoints {

    @GET("admin")
    fun getAdmin(): Call<List<Admin>>

    @GET("admin/{admin_uid}")
    fun getAdmin( @Query("admin_uid") adminUid: String): Call<Admin>

    @POST("admin")
    fun postAdmin( @Body admin: Admin): Call<Admin>

    @PUT("admin/{admin_uid}")
    fun putAdmin( @Query("admin_uid") adminUid: String, @Body admin: Admin): Call<Admin>

    // Admin endpoints for batch

    @GET("admin/{admin_uid}/batch")
    fun getBatchForAdmin( @Query("admin_uid") adminUid: String): Call<List<Batch>>

    @POST("admin/{admin_uid}/batch")
    fun postBatchForAdmin( @Query("admin_uid") adminUid: String,
                           @Body batch: Batch): Call<Batch>
    @PUT("admin/{admin_uid}/batch/{batch_id}")
    fun putBatchForAdmin( @Query("admin_uid") adminUid: String,
                          @Query("batch_id") batchId:String,
                          @Body batch: Batch): Call<Batch>

    @DELETE("admin/{admin_uid}/batch/{batch_id}")
    fun deleteBatchForAdmin( @Query("admin_uid") adminUid: String,
                             @Query("batch_id") batchId:Int):Call<Void>

    //Admin endpoints for staff

    @GET("admin/{admin_uid}/batch/staff/{staffid}")
    fun getAllBatchForStaff(@Query("admin_uid") admin_uid:String,
                            @Query("staffid") staffid:String):Call<List<Batch>>

    @POST("admin/{admin_uid}/staff")
    fun postStaff(@Query("admin_uid") admin_uid:String,
                  @Body staff: Staff):Call<Staff>

    @GET("admin/{admin_uid}/staff")
    fun getAllStaffForAdmin(@Query("admin_uid") admin_uid:String):Call<List<Staff>>

    @PUT("admin/{admin_uid}/staff/{staff_id}")
    fun putStaff(@Query("admin_uid") admin_uid:String,
                 @Query("staff_id") staffid:String,
                 @Body staff: Staff):Call<Staff>

    @DELETE("admin/{admin_uid}/staff/{staff_id}")
    fun deleteStaff(@Query("admin_uid") admin_uid:String,
                    @Query("staff_id") staffid:String):Call<Void>

}