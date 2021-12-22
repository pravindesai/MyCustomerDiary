package com.pravin.barcodeapp.mycustomer.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pravin.barcodeapp.mycustomer.R
import kotlinx.android.synthetic.main.otpmaterialdialog.view.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.pravin.barcodeapp.mycustomer.Util.Constants
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.otpmaterialdialog.*
import java.util.concurrent.TimeUnit


class OtpDialog(val context:Activity, val bundle: Bundle) : DialogFragment() {
    interface OnDialogResultPublished{
        fun onSucess(adminUid:String, sname:String, phone: String)
        fun onCancelled()
        fun onFailed()
    }
    val TAG = "**"+this::class.java.simpleName
    var  onDialogResultPublished:OnDialogResultPublished? = null
    lateinit var auth: FirebaseAuth
    lateinit var verificationCode: String

    lateinit var sname   :String
    lateinit var phone   :String
    lateinit var password:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView  = layoutInflater.inflate(R.layout.otpmaterialdialog, container, false)
        return dialogView
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        val back = ColorDrawable(Color.TRANSPARENT)
        val margin = 20
        val inset = InsetDrawable(back, margin)

        if (dialog != null) {
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                                     ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(inset)
            dialog.setCancelable(false)
        }

    }

    override fun onViewCreated(dialog: View, savedInstanceState: Bundle?) {
        super.onViewCreated(dialog, savedInstanceState)
        dialog.otpEt.requestFocus()
        auth     = FirebaseAuth.getInstance()
        sname    = bundle.getString(Constants.sname)!!
        phone    = bundle.getString(Constants.phone)!!
        password = bundle.getString(Constants.password)!!


        sendVerificationCode(Constants.COUNTRY_PREFIX+phone)

        dialog.submitButton.setOnClickListener {
            val otp:String = otpEt.text.toString()
            if (otp.trim().isEmpty()){
                OtpTextLayout.error = "Required"
                return@setOnClickListener
            }else{
                OtpTextLayout.error = ""
            }
            signInWithPhoneAuthCredential(otp)
        }

        dialog.closeButton.setOnClickListener {
            onDialogResultPublished?.onCancelled()
            dialog.otpEt.text?.clear()
            dismiss()
        }
    }

    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)                  // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context)                 // Activity (for callback binding)
            .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.e(TAG, "send otp : phone"+phone )

    }

    val mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code = credential.smsCode
            Log.e(TAG, "onVerificationCompleted: $code" )
            if (code!=null){
                signInWithPhoneAuthCredential(code)
            }else{
                signInWithPhoneAuthCredential(credential)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            onDialogResultPublished?.onFailed()
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            Log.e(TAG, "onCodeSent ")
            verificationCode = verificationId
            overlay.visibility = View.GONE

        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){

                if (auth.currentUser?.email.isNullOrBlank()) {
                    linkMailForPasswordAuth()
                }else{
                    Log.e(TAG, "signInWithPhoneAuthCredential: "+auth.currentUser?.email )
                }
                this.dismiss()
                onDialogResultPublished?.onSucess(auth.currentUser?.uid.toString(), sname, phone)
            }else{
                Log.e(TAG, "signInWithPhoneAuthCredential: FAILED" )
                onDialogResultPublished?.onFailed()
                dismiss()
            }
        }.addOnFailureListener {
            onDialogResultPublished?.onFailed()
            dismiss()
            Log.e(TAG, "signInWithPhoneAuthCredential: FAILED2" )
        }
        OtpTextLayout.boxStrokeColor = Color.RED
    }
    private fun signInWithPhoneAuthCredential(code:String) {
        val credential = PhoneAuthProvider.getCredential(verificationCode, code)
        signInWithPhoneAuthCredential(credential)
    }

    //2021
    /*
        x`Firebase dose not provide Phone + Password verification after otp verification
        x`for that creating uniques mail based on phone number and using mail + password verification
    */
    private fun linkMailForPasswordAuth() {
        val phoneMail = phone+Constants.mailExtension;
        val password = password;
        val authCredential: AuthCredential = EmailAuthProvider.getCredential(phoneMail, password);

        auth.currentUser?.linkWithCredential(authCredential)?.addOnCompleteListener {
            if (it.isSuccessful){
                Log.e(TAG, "linkMailForPasswordAuth: Linked Sucessfully" )
            }else{
                Log.e(TAG, "linkMailForPasswordAuth: Linked Failed "+it.exception )
            }
        }?.addOnFailureListener {
            Log.e(TAG, "linkMailForPasswordAuth: Linked Failed messsage "+it.message )
        }
    }

}