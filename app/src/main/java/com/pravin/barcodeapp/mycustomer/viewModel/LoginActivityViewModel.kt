package com.pravin.barcodeapp.mycustomer.viewModel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.Util.FirebaseUtil
import com.pravin.barcodeapp.mycustomer.Util.UniversalProgressDialog
import com.pravin.barcodeapp.mycustomer.retrofit.BaseDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivityViewModel: ViewModel() {
    interface OnViewModelResultPublished{
        fun userAlreadyExists(phonenumber:String)
        fun userNotFound(phonenumber: String)
        fun authenticationSucessful(phonenumber: String)
        fun authenticationFailed()
        fun openOtpDialog(bundle: Bundle)
    }
    var onViewModelResultPublished: OnViewModelResultPublished? = null
    private val baseDataRepository: BaseDataRepository

    var auth:FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
        baseDataRepository = BaseDataRepository()
    }

    fun getGenderOptions()          = baseDataRepository.getGenederOptions()
    fun getStatusOptions()          = baseDataRepository.getStatusOptions()
    fun getTypeOptions()            = baseDataRepository.getTypeOptions()


    fun login(phone: String, pass: String) {
        isUserExists("", phone, pass, Constants.MODE_LOGIN)

    }

    fun signup(serviceName:String, phone: String, pass: String) {
        isUserExists(serviceName, phone, pass, Constants.MODE_SIGNUP)
    }

    fun isUserExists(serviceName: String, phoneNumber:String, pass:String, MODE:Boolean){
        FirebaseUtil.auth = FirebaseAuth.getInstance()
        val phoneMail = Constants.COUNTRY_PREFIX+phoneNumber+Constants.mailExtension
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
                                        onViewModelResultPublished?.authenticationSucessful(phoneNumber)
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


}