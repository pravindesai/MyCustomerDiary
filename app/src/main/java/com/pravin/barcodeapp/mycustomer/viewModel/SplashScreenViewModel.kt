package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pravin.barcodeapp.mycustomer.dao.GenderDao
import com.pravin.barcodeapp.mycustomer.dao.StatusDao
import com.pravin.barcodeapp.mycustomer.dao.TypeDao
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.model.Gender
import com.pravin.barcodeapp.mycustomer.model.Status
import com.pravin.barcodeapp.mycustomer.model.Type
import com.pravin.barcodeapp.mycustomer.retrofit.BaseDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() :ViewModel() {
    val TAG = "**"+this::class.java.simpleName

    @Inject
    lateinit var baseDataRepository: BaseDataRepository
    @Inject
    lateinit var genderDao: GenderDao
    @Inject
    lateinit var typeDao: TypeDao
    @Inject
    lateinit var statusDao: StatusDao

    fun getGenderOptions()          = baseDataRepository.getGenederOptions()
    fun getStatusOptions()          = baseDataRepository.getStatusOptions()
    fun getTypeOptions()            = baseDataRepository.getTypeOptions()

    fun downloadBaseData(){

        viewModelScope.launch(Dispatchers.IO){
            if (genderDao.getAll().isEmpty()) {

                getGenderOptions().collect() {
                    it.forEach {
                        genderDao.insert(Gender(0,it))
                    }
                }

                getStatusOptions().collect {
                    it.forEach {
                        statusDao.insert(Status(0,it))
                    }
                }

                getTypeOptions().collect {
                    it.forEach {
                        typeDao.insert(Type(0,it))
                    }
                }
            }
        }
    }

}