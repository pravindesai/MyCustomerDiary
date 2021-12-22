package com.pravin.barcodeapp.mycustomer.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import androidx.core.app.ActivityOptionsCompat




class SplashScreen : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

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