package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.service.StaffEndpoints

class StaffRepository:RetrofitRepository() {
    private val staffEndpoints: StaffEndpoints
    init {
        staffEndpoints = retrofit.create(StaffEndpoints::class.java)
    }

}