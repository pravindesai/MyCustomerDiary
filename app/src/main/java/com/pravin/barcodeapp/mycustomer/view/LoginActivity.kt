package com.pravin.barcodeapp.mycustomer.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.animation.content.Content
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.FirebaseUtil
import com.pravin.barcodeapp.mycustomer.Util.UniversalProgressDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    var LAYOUT_SWITCH:Boolean = true
    var MODE = Constants.MODE_LOGIN
    lateinit var otpDialog:OtpDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        otpDialog = OtpDialog()
        otpDialog.onDialogResultPublished = DialogDelegation()

        loginButton.setOnClickListener {
            if(MODE == Constants.MODE_LOGIN){

                UniversalProgressDialog.show(this)
                Log.e(TAG, "onCreate: isUserExists "+FirebaseUtil.isUserExists("7020529425") )
                UniversalProgressDialog.hide()

            }else if (MODE == Constants.MODE_SIGNUP){
                otpDialog.show(this.supportFragmentManager, "")

            }

        }

    }

    fun switchLayout(view: android.view.View) {
        LAYOUT_SWITCH = !LAYOUT_SWITCH

        if (LAYOUT_SWITCH){
            MODE = Constants.MODE_LOGIN
            ServiceNameLayout.visibility = View.GONE
            loginButton.text = getString(R.string.login)
            switchButton.text = getString(R.string.don_t_have_account_create_one)
        }else{
            MODE = Constants.MODE_SIGNUP
            ServiceNameLayout.visibility = View.VISIBLE
            loginButton.text = getString(R.string.sign_up)
            switchButton.text = getString(R.string.already_have_account_login)
        }
    }

    class DialogDelegation :OtpDialog.OnDialogResultPublished{
        val TAG = "**"+this::class.java.simpleName
        override fun onSucess(adminUid: String) {
            Log.e(TAG, "onSucess: " )
        }

        override fun onCancelled() {
            Log.e(TAG, "onCancelled: " )
        }

    }
}