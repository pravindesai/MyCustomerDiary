package com.pravin.barcodeapp.mycustomer.view

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pravin.barcodeapp.mycustomer.R
import com.pravin.barcodeapp.mycustomer.model.Admin
import kotlinx.android.synthetic.main.otpmaterialdialog.view.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable


class OtpDialog: DialogFragment() {
    interface OnDialogResultPublished{
        fun onSucess(adminUid:String)
        fun onCancelled()
    }
    var  onDialogResultPublished:OnDialogResultPublished? = null
        get() {
            return field
        }
        set(value) {
        field = value
    }

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

        dialog.submitButton.setOnClickListener {

        }

        dialog.closeButton.setOnClickListener {
            onDialogResultPublished?.onCancelled()
            dialog.otpEt.text?.clear()
            dismiss()
        }
    }

}