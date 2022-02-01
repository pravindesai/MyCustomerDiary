package com.pravin.barcodeapp.mycustomer.sealedclasses

import com.pravin.barcodeapp.mycustomer.model.Customer

sealed class StaffEvents {
    object Empty:StaffEvents()
    data class Loading(var message:String):StaffEvents()
    data class Failure(var message:String):StaffEvents()
    data class PostSuccess(val customer: Customer):StaffEvents()
    data class PutSuccess(val customer: Customer):StaffEvents()
    object DeletSuccess:StaffEvents()
    data class GetSucess(val customerList: List<Customer>):StaffEvents()
}