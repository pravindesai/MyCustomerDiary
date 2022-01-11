package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pravin.barcodeapp.mycustomer.viewModel.SplashScreenViewModel
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class SplashScreen : BaseActivity() {
    lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenViewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        downloadBaseData()

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

    fun downloadBaseData(){

            if (roomDb.genderDao().getAll().size <= 0) {
                splashScreenViewModel.getGenderOptions().observe(this,{ genderList->
                    roomDb.genderDao().insertAll(genderList)
                })
            }

            if (roomDb.statusDao().getAll().size <= 0) {
                splashScreenViewModel.getStatusOptions().observe(this,{ statuslist ->
                        roomDb.statusDao().insertAll(statuslist)
                })
            }

            if (roomDb.typeDao().getAll().size <= 0) {
                splashScreenViewModel.getTypeOptions().observe(this,{ typelist->
                        roomDb.typeDao().insertAll(typelist)
                })
            }
    }

}