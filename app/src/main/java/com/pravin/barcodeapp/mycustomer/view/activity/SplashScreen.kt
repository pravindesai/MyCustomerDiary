package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pravin.barcodeapp.mycustomer.viewModel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@AndroidEntryPoint
class SplashScreen : BaseActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenViewModel.downloadBaseData()

        Handler(Looper.getMainLooper()).postDelayed({
            //If user not logged in already else
            val bundle = ActivityOptionsCompat.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
            if (FirebaseAuth.getInstance().currentUser==null){
                val loginInActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginInActivity, bundle)
                finish()
            }else{
                val profileActivity = Intent(this, MainDashboardActivity::class.java)
                startActivity(profileActivity, bundle)
                finish()
            }

        }, 3000)

    }

}