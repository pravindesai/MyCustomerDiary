package com.pravin.barcodeapp.mycustomer.service

import com.pravin.barcodeapp.mycustomer.model.Customer
import retrofit2.Call
import retrofit2.http.*

interface StaffEndpoints {

    @POST("staff/{staff_id}/customer")
    fun postCustomer(@Query("staff_id") staff_id:Int,
                    @Body customer: Customer):Call<Customer>

    @GET("staff/{staffid}/customer")
    fun getCustomerForStaff(@Query("staffid") staff_id:Int):Call<List<Customer>>

    @PUT("staff/{staffid}/customer/{customerId}")
    fun putCustomer(@Query("staffid") staff_id:Int,
                    @Query("customerId") customerId:Int,
                    @Body customer: Customer):Call<Customer>

    @DELETE("staff/{staffid}/customer/{customerId}")
    fun deleteCustomer(@Query("staffid") staff_id:Int,
                       @Query("customerId") customerId:Int):Call<Void>

}