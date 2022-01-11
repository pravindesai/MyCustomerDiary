package com.pravin.barcodeapp.mycustomer.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.retrofit.AdminRepository
import com.pravin.barcodeapp.mycustomer.retrofit.BaseDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) :AndroidViewModel(application) {
    val TAG = "**"+this::class.java.simpleName

    private val baseDataRepository: BaseDataRepository

    init {
        baseDataRepository = BaseDataRepository()
    }

    fun getGenderOptions()          = baseDataRepository.getGenederOptions()
    fun getStatusOptions()          = baseDataRepository.getStatusOptions()
    fun getTypeOptions()            = baseDataRepository.getTypeOptions()


}