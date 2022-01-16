package com.pravin.barcodeapp.mycustomer.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.SessionManager
import com.pravin.barcodeapp.mycustomer.model.Admin


class HomeFragment : Fragment() {

    val TAG = "**"+this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        val admin:Admin = SessionManager.getAdmin(Constants.KEY_CURRENT_ADMIN)
        Log.e(TAG, "onStart: Current Admin "+admin )

    }
}