package com.pravin.barcodeapp.mycustomer.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.Util.BaseActivity
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.SessionManager
import com.pravin.barcodeapp.mycustomer.Util.UniversalProgressDialog
import com.pravin.barcodeapp.mycustomer.model.Address
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.view.dialog.OtpDialog
import com.pravin.barcodeapp.mycustomer.viewModel.LoginActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    companion object{
        private val SIGN_IN_METHOD_PASSWORD = "PASSWORD"
        private val SIGN_IN_METHOD_OTP = "OTP"
    }

    var LAYOUT_SWITCH:Boolean = true
    var MODE = Constants.MODE_LOGIN
    private val loginActivityViewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginActivityViewModel.onViewModelResultPublished = LoginViewModelDelegation(this)

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

    fun loginClick(view: android.view.View) {

        val sname:String = servicenameET.text.toString()
        val phone:String = phoneEt.text.toString()
        val pass :String = passwordEt.text.toString()

        if (MODE == Constants.MODE_SIGNUP && (sname.trim().isBlank() || sname.trim().isEmpty())){
            ServiceNameLayout.error = "Required"
            return
        }else{
            ServiceNameLayout.error = ""
        }
        if (phone.trim().isBlank() || phone.trim().isEmpty()){
            PhoneNumberLayout.error = "Required"
            return
        }else{
            PhoneNumberLayout.error = ""
        }

        if (pass.trim().isBlank() || pass.trim().isEmpty()){
            TextPasswordLayout.error = "Required"
            return
        }else if (pass.length<6){
            TextPasswordLayout.error = ("Password lenght should be 6 characters")
            TextPasswordLayout.requestFocus()
            return
        }else{
            TextPasswordLayout.error = ""
        }



        if(MODE == Constants.MODE_LOGIN){
            UniversalProgressDialog.show(this)
            loginActivityViewModel.login(phone, pass)

        }else if (MODE == Constants.MODE_SIGNUP){
//            UniversalProgressDialog.show(this)
            loginActivityViewModel.signup(sname ,phone, pass)
        }
    }

    inner class DialogDelegation(val context: LoginActivity)
        : OtpDialog.OnDialogResultPublished {
        val TAG = "**"+this::class.java.simpleName
        val activity = context

        override fun onSucess(adminUid: String, sname:String, phonenumber: String, password:String) {
            Log.e(TAG, "onSucess: " )

                UniversalProgressDialog.hide()

                val intent = Intent(context, MainDashboardActivity::class.java)
                intent.putExtra( Constants.adminUid, adminUid)
                intent.putExtra( Constants.sname,    sname)
                intent.putExtra( Constants.phone,    phonenumber)
                intent.putExtra( Constants.password, password)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                var signInUser = Admin(0, Address(0,"","","","",0,"")
                    ,adminUid, phonenumber+Constants.mailExtension,phonenumber,sname,
                    "ACTIVE","FREE")
                    SessionManager.saveAdmin(Constants.KEY_CURRENT_ADMIN,signInUser)

                    UniversalProgressDialog.show(context)
                    loginActivityViewModel.postAdmin(signInUser).observe(context, {
                        Log.e(TAG, "onSucess: "+it )
                        UniversalProgressDialog.hide()
                        context.startActivity(intent)
                        context.finishAffinity()

                    })

        }

        override fun onCancelled() {
            Log.e(TAG, "onCancelled: " )

            UniversalProgressDialog.hide()
        }
        override fun onFailed() {
            Log.e(TAG, "onFailed: " )
            UniversalProgressDialog.hide()
        }


    }
    inner class LoginViewModelDelegation(val activity: LoginActivity)
        :LoginActivityViewModel.OnViewModelResultPublished{
        val TAG = "**"+this::class.java.simpleName

        override fun userAlreadyExists(phonenumber: String) {
            activity.PhoneNumberLayout.error = "User Already Exists"
            activity.PhoneNumberLayout.requestFocus()
            Log.e(TAG, "userExists: "+phonenumber )
            UniversalProgressDialog.hide()
        }
        override fun userNotFound(phonenumber: String) {
            activity.PhoneNumberLayout.error = "User Not Found"
            activity.PhoneNumberLayout.requestFocus()
            Log.e(TAG, "userNotFound: "+phonenumber )
            UniversalProgressDialog.hide()
        }
        override fun authenticationSucessful(phonenumber: String, adminUid: String) {
            Log.e(TAG, "Authentication sucessful: "+phonenumber )

            UniversalProgressDialog.hide()

            val intent = Intent(activity, MainDashboardActivity::class.java)
            intent.putExtra( Constants.phone,   phonenumber)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            UniversalProgressDialog.show(activity)

            Log.e(TAG, "looking for admin : "+adminUid )
            loginActivityViewModel.getAdmin(adminUid).observe(activity,{
                SessionManager.saveAdmin(Constants.KEY_CURRENT_ADMIN, it)
                activity.startActivity(intent)
                activity.finishAffinity()

                UniversalProgressDialog.hide()
            })





        }
        override fun authenticationFailed() {
            activity.TextPasswordLayout.error = "Authentication Failed"
            Log.e(TAG, "Authentication Failed: " )

            activity.TextPasswordLayout.requestFocus()
            UniversalProgressDialog.hide()
        }
        override fun openOtpDialog(bundle: Bundle) {
            val otpDialog = OtpDialog(activity, bundle)
            otpDialog.onDialogResultPublished = DialogDelegation(activity)
            otpDialog.show(activity.supportFragmentManager, "")
            UniversalProgressDialog.hide()
        }

    }
}