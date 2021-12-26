package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.viewModel.SplashScreenViewModel


class SplashScreen : BaseActivity() {
    lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenViewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

//        if (roomDb.genderDao().getAll().size <= 0){
//            splashScreenViewModel.getGenderOptions().observe(this, {
//                roomDb.genderDao().insertAll(it)
//            })
//        }

        roomDb.genderDao().insert("MALE")

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