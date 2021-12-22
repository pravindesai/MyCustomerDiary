package com.pravin.barcodeapp.mycustomer.Util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.android.synthetic.main.activity_login.*

object FirebaseUtil {
    var auth: FirebaseAuth
    init {
        auth = FirebaseAuth.getInstance()
    }
}