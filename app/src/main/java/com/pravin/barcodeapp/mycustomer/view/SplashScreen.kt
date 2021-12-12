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

class SplashScreen : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            //If user not logged in already else
            if (FirebaseAuth.getInstance().currentUser==null){
                val loginInActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginInActivity)
                finish()
            }else{
                val profileActivity = Intent(this, MainDashboardActivity::class.java)
                startActivity(profileActivity)
                finish()
            }

        }, 3000)


    }
}