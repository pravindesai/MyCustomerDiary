package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.model.Customer
import com.pravin.barcodeapp.mycustomer.service.StaffEndpoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.*
import javax.inject.Inject

class StaffRepository @Inject constructor():RetrofitRepository() {
    @Inject
    lateinit var staffEndpoints: StaffEndpoints

    suspend fun postCustomer( staff_id:Int, customer: Customer): Flow<Customer>{
        val result = staffEndpoints.postCustomer(staff_id, customer)
        return flow {
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCustomerForStaff(staff_id:Int):Flow<List<Customer>>{
        val result = staffEndpoints.getCustomerForStaff(staff_id)
        return flow {
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun putCustomer(staff_id:Int, customerId:Int, customer: Customer): Flow<Customer>{
        val result = staffEndpoints.putCustomer(staff_id, customerId, customer)
        return flow {
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteCustomer(staff_id:Int, customerId:Int):Flow<Void>{
        val result = staffEndpoints.deleteCustomer(staff_id, customerId)
        return flow {
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}