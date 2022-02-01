package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Batch
import com.pravin.barcodeapp.mycustomer.retrofit.AdminRepository
import com.pravin.barcodeapp.mycustomer.retrofit.StaffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainDashboardViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var adminRepository:AdminRepository
    @Inject
    lateinit var staffRepository: StaffRepository

    fun getStaff(adminUid:String, phonenumber: String) = adminRepository.getStaff(adminUid, phonenumber)


}