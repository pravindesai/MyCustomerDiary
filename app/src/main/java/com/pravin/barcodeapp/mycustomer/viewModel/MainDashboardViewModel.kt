package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Batch
import com.pravin.barcodeapp.mycustomer.retrofit.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainDashboardViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var adminRepository:AdminRepository


    fun getAdmin()                  = adminRepository.getAdmin()
    fun getAdmin(admin_ui:String)   = adminRepository.getAdmin(admin_ui)
    fun postAdmin(admin:Admin)      = adminRepository.postAdmin(admin)
    fun updateAdmin(adminuid:String
                    , admin:Admin)  = adminRepository.putAdmin(adminuid,admin)


    fun getBatchForAdmin(adminuid: String)    = adminRepository.getBatchForAdmin(adminuid)
    fun postBatchForAdmin(adminuid: String
                          , batch: Batch)     = adminRepository.postBatchForAdmin(adminuid, batch)
    fun putBatchForAdmin(adminuid: String
                         , batchId:String
                         , batch: Batch)      = adminRepository.putBatchForAdmin(adminuid, batchId,batch)
    fun deleteBatchForAdmin(adminuid: String
                            , batchId:Int)    = adminRepository.deleteBatchForAdmin(adminuid, batchId)

}