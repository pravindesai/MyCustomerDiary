package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.Util.RetrofitRepository
import com.pravin.barcodeapp.mycustomer.service.StaffEndpoints
import javax.inject.Inject

class StaffRepository @Inject constructor():RetrofitRepository() {
    @Inject
    lateinit var staffEndpoints: StaffEndpoints


}