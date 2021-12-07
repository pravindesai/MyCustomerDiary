package com.pravin.barcodeapp.mycustomer.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.SessionManager
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

    }

    fun clicked(view: android.view.View) {

    }
}