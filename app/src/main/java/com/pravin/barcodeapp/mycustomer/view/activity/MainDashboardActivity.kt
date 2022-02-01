package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.*
import com.pravin.barcodeapp.mycustomer.model.Staff
import com.pravin.barcodeapp.mycustomer.viewModel.MainDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_drawer_header.*
import kotlinx.android.synthetic.main.navigation_drawer_header.view.*

@AndroidEntryPoint
class MainDashboardActivity : BaseActivity() {

    val mainDashboardViewModel:MainDashboardViewModel by viewModels()

    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!

        hamburger.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
            hamburger.animate().alpha(0f).duration = 200
        }

        navigation_drawer.getHeaderView(0).close.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START);
            hamburger.animate().alpha(1f).duration = 200
        }

        navigation_drawer.setNavigationItemSelectedListener{
            when(it.title.toString()){
                "LOGOUT" -> {
                    MaterialAlertDialogBuilder(this@MainDashboardActivity)
                        .setMessage("Log Out")
                        .setNegativeButton("Cancel"){
                            dialog, which-> dialog.dismiss()
                        }
                        .setPositiveButton("Logout"){

                            dialog,which-> signOut()
                        }
                        .show()
                    true
                }
                else-> true
            }
        }


    }

    override fun onStart() {
        super.onStart()
        phoneNumber = intent.getStringExtra(Constants.phone).toString()
        if (!phoneNumber.trim().isBlank() || !phoneNumber.trim().isEmpty()) {
            mainDashboardViewModel.getStaff(firebaseUser.uid, phoneNumber).observe(this, {
                SessionManager.saveStaff(Constants.KEY_CURRENT_STAFF, it)
            })
        }
    }

    private fun signOut() {
        UniversalProgressDialog.show(this@MainDashboardActivity)
        firebaseAuth.signOut()
        val loginIntent = Intent(this@MainDashboardActivity, LoginActivity::class.java)
        UniversalProgressDialog.hide()

        SessionManager.delete(Constants.KEY_CURRENT_ADMIN)
        SessionManager.delete(Constants.KEY_CURRENT_STAFF)

        startActivity(loginIntent)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        finishAffinity()
    }


}
