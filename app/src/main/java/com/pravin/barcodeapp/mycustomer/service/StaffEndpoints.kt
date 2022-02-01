package com.pravin.barcodeapp.mycustomer.service

import com.pravin.barcodeapp.mycustomer.model.Customer
import retrofit2.Call
import retrofit2.http.*

interface StaffEndpoints {

    @POST("staff/{staff_id}/customer")
    suspend fun postCustomer(@Query("staff_id") staff_id:Int,
                    @Body customer: Customer):Customer

    @GET("staff/{staffid}/customer")
    suspend fun getCustomerForStaff(@Query("staffid") staff_id:Int):List<Customer>

    @PUT("staff/{staffid}/customer/{customerId}")
    suspend fun putCustomer(@Query("staffid") staff_id:Int,
                    @Query("customerId") customerId:Int,
                    @Body customer: Customer):Customer

    @DELETE("staff/{staffid}/customer/{customerId}")
    suspend fun deleteCustomer(@Query("staffid") staff_id:Int,
                       @Query("customerId") customerId:Int):Void

}