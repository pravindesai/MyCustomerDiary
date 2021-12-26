package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.*
import com.pravin.barcodeapp.mycustomer.viewModel.MainDashboardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_drawer_header.*
import kotlinx.android.synthetic.main.navigation_drawer_header.view.*

class MainDashboardActivity : BaseActivity() {

    lateinit var mainDashboardViewModel:MainDashboardViewModel
    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainDashboardViewModel = ViewModelProvider(this).get(MainDashboardViewModel::class.java)

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

        Log.e(TAG, "onStart: "+roomDb.genderDao().getAll() )
    }

    private fun signOut() {
        UniversalProgressDialog.show(this@MainDashboardActivity)
        firebaseAuth.signOut()
        val loginIntent = Intent(this@MainDashboardActivity, LoginActivity::class.java)
        UniversalProgressDialog.hide()
        startActivity(loginIntent)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        finishAffinity()
    }

}
