package com.pravin.barcodeapp.mycustomer.viewModel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.FirebaseUtil
import com.pravin.barcodeapp.mycustomer.Util.UniversalProgressDialog
import com.pravin.barcodeapp.mycustomer.model.Admin
import com.pravin.barcodeapp.mycustomer.retrofit.AdminRepository

class LoginActivityViewModel: ViewModel() {
    interface OnViewModelResultPublished{
        fun userAlreadyExists(phonenumber:String)
        fun userNotFound(phonenumber: String)
        fun authenticationSucessful(phonenumber: String, admin_uid: String)
        fun authenticationFailed()
        fun openOtpDialog(bundle: Bundle)
    }
    var onViewModelResultPublished: OnViewModelResultPublished? = null
    var adminRepository:AdminRepository

    var auth:FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
        adminRepository = AdminRepository()
    }


    fun login(phone: String, pass: String) {
        isUserExists("", phone, pass, Constants.MODE_LOGIN)

    }

    fun signup(serviceName:String, phone: String, pass: String) {
        isUserExists(serviceName, phone, pass, Constants.MODE_SIGNUP)
    }

    fun isUserExists(serviceName: String, phoneNumber:String, pass:String, MODE:Boolean){
        FirebaseUtil.auth = FirebaseAuth.getInstance()
        val phoneMail = phoneNumber+Constants.mailExtension
        Log.e("**", "isUserExists: "+phoneMail )
        FirebaseUtil.auth.fetchSignInMethodsForEmail(phoneMail).addOnCompleteListener {
            if (it.isSuccessful){
                val result: SignInMethodQueryResult? = it.result

                if (result?.signInMethods?.size != 0){

                        if(MODE == Constants.MODE_LOGIN){
                            auth.signInWithEmailAndPassword(phoneMail, pass)
                                .addOnCompleteListener() { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information

                                        UniversalProgressDialog.hide()
                                        onViewModelResultPublished?.authenticationSucessful(phoneNumber, auth.currentUser?.uid.toString())
                                    } else {
                                        onViewModelResultPublished?.authenticationFailed()
                                        UniversalProgressDialog.hide()
                                    }
                                }
                        }else if (MODE == Constants.MODE_SIGNUP){
                            onViewModelResultPublished?.userAlreadyExists(phoneNumber)
                        }

                }else{

                        if(MODE == Constants.MODE_LOGIN){
                            onViewModelResultPublished?.userNotFound(phoneNumber)
                        }else if (MODE == Constants.MODE_SIGNUP){
                            val bundle:Bundle = Bundle()
                            bundle.putString(Constants.sname, serviceName)
                            bundle.putString(Constants.phone, phoneNumber)
                            bundle.putString(Constants.password, pass)

                            onViewModelResultPublished?.openOtpDialog(bundle)
                        }
                }

            }else{
                Log.e("**", "fetchSignInMethods: Failed--> " + it.exception )
                onViewModelResultPublished?.userNotFound(phoneNumber)
            }
        }
    }

    fun postAdmin(admin: Admin):MutableLiveData<Admin> = adminRepository.postAdmin(admin)

    fun getAdmin(adminUid:String):MutableLiveData<Admin> = adminRepository.getAdmin(adminUid)



}