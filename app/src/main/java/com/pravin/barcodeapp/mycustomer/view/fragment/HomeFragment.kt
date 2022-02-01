package com.pravin.barcodeapp.mycustomer.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.SessionManager
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.model.Staff
import com.pravin.barcodeapp.mycustomer.view.activity.MainDashboardActivity
import com.pravin.barcodeapp.mycustomer.viewModel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val TAG = "**"+this::class.java.simpleName
    private val viewModel:HomeFragmentViewModel by viewModels()

    lateinit var addCustomerImg:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        addCustomerImg = rootView.addCustomerImg
        addCustomerImg.setOnClickListener(AddCustomerListener())


        return rootView
    }

    override fun onStart() {
        super.onStart()

    }

    class AddCustomerListener:View.OnClickListener{
        override fun onClick(v: View) {
            Snackbar.make(v,"Add customer", Snackbar.LENGTH_SHORT).show()

        }
    }
}