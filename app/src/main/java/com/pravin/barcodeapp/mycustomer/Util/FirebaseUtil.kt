package com.pravin.barcodeapp.mycustomer.Util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.android.synthetic.main.activity_login.*

object FirebaseUtil {
    val auth: FirebaseAuth
    init {
        auth = FirebaseAuth.getInstance()
    }
    fun isUserExists(phoneNumber:String):Boolean{
        var isUserExists:Boolean = false
        auth.fetchSignInMethodsForEmail(phoneNumber+Constants.mailExtension).addOnCompleteListener {
            if (it.isSuccessful){
                val result: SignInMethodQueryResult? = it.result
                if (result!=null && result.signInMethods!= null && result.signInMethods!!.size > 0){
                    //User already exists
                    isUserExists = true
                }else{
                    //No User found for this mail
                    isUserExists = false
                }
            }else{
                Log.e("**", "fetchSignInMethods: Failed" )
                isUserExists = false
            }
        }
        return isUserExists
    }
}