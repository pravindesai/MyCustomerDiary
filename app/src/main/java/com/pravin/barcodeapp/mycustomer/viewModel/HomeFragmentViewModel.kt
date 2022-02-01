package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pravin.barcodeapp.mycustomer.database.RoomDb
import com.pravin.barcodeapp.mycustomer.model.Customer
import com.pravin.barcodeapp.mycustomer.retrofit.StaffRepository
import com.pravin.barcodeapp.mycustomer.sealedclasses.StaffEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor():BaseViewModel() {



}