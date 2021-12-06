package com.pravin.barcodeapp.mycustomer.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import com.pravin.barcodeapp.mycustomer.model.Address
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Batch
import com.pravin.barcodeapp.mycustomer.viewModel.MainDashboardViewModel

class MainDashboardActivity : BaseActivity() {

    lateinit var mainDashboardViewModel:MainDashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainDashboardViewModel = ViewModelProvider(this).get(MainDashboardViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        val adminUID = "XYZ"
        val adminUidGenerated:String = System.currentTimeMillis().div(2).toString()
        val address:Address = Address("Sara sankrity", "Chakan", "IN",0,"near xberia", 410501,"MH")
        val admin:Admin = Admin(address, adminUID,adminUidGenerated+"@gmail.com", 0,
            "7020529425", adminUidGenerated+"_Service","MH","ACTIVE")

        val batch:Batch = Batch(adminUID, "batch description", 1,1234, 199, 0, 4, 432, "TYPE1")

//        mainDashboardViewModel.getGenderOptions()
//        mainDashboardViewModel.getStatusOptions()
//        mainDashboardViewModel.getTypeOptions()
//        mainDashboardViewModel.getAdmin()
//        mainDashboardViewModel.getAdmin(adminUID)
//        mainDashboardViewModel.postAdmin(admin)
//        mainDashboardViewModel.updateAdmin(adminUID, admin)

//        mainDashboardViewModel.postBatchForAdmin(adminUID, batch)
//        mainDashboardViewModel.getBatchForAdmin(adminUID)
        batch.fee = 299
        batch.id = 65
//        mainDashboardViewModel.putBatchForAdmin(adminUID, batch.id.toString(), batch)
//        mainDashboardViewModel.deleteBatchForAdmin(adminUID, batch.id)


    }

    fun clicked(view: android.view.View) {

    }
}