package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.retrofit.AdminRepository
import com.pravin.barcodeapp.mycustomer.retrofit.BaseDataRepository

class SplashScreenViewModel:ViewModel() {
    private val baseDataRepository: BaseDataRepository

    init {
        baseDataRepository = BaseDataRepository()
    }

    fun getGenderOptions()          = baseDataRepository.getGenederOptions()
    fun getStatusOptions()          = baseDataRepository.getStatusOptions()
    fun getTypeOptions()            = baseDataRepository.getTypeOptions()

}