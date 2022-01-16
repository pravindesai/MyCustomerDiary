package com.pravin.barcodeapp.mycustomer.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.pravin.barcodeapp.mycustomer.retrofit.BaseDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.multibindings.IntKey
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() :ViewModel() {
    val TAG = "**"+this::class.java.simpleName

    @Inject
    lateinit var baseDataRepository: BaseDataRepository

    fun getGenderOptions()          = baseDataRepository.getGenederOptions()
    fun getStatusOptions()          = baseDataRepository.getStatusOptions()
    fun getTypeOptions()            = baseDataRepository.getTypeOptions()
    
}