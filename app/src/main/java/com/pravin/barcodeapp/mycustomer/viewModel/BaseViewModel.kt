package com.pravin.barcodeapp.mycustomer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
open class BaseViewModel @Inject constructor():ViewModel() {

    @Inject
    lateinit var staffRepository: StaffRepository

    private val staffResponseState: MutableStateFlow<StaffEvents> = MutableStateFlow(StaffEvents.Empty)
    val _staffResponseState: StateFlow<StaffEvents> = staffResponseState

    fun postCustomer(staff_id:Int, customer: Customer) = viewModelScope.launch(Dispatchers.IO){
        staffResponseState.value = StaffEvents.Loading("Adding Customer...")
        staffRepository.postCustomer(staff_id, customer).catch {e->
            staffResponseState.value = StaffEvents.Failure(e.message.toString())
        }.collect { customer->
            staffResponseState.value = StaffEvents.PostSuccess(customer)
        }
    }
    fun getCustomerForStaff(staff_id:Int) = viewModelScope.launch(Dispatchers.IO){
        staffResponseState.value = StaffEvents.Loading("Getting Customers...")
        staffRepository.getCustomerForStaff(staff_id).catch {e->
            staffResponseState.value = StaffEvents.Failure(e.message.toString())
        }.collect { customerList->
            staffResponseState.value = StaffEvents.GetSucess(customerList)
        }
    }
    fun putCustomer(staff_id:Int, customerId:Int, customer: Customer) = viewModelScope.launch(Dispatchers.IO){
        staffResponseState.value = StaffEvents.Loading("Updating Customer...")
        staffRepository.putCustomer(staff_id, customerId, customer).catch {e->
            staffResponseState.value = StaffEvents.Failure(e.message.toString())
        }.collect { customer->
            staffResponseState.value = StaffEvents.PostSuccess(customer)
        }
    }
    fun deleteCustomer(staff_id:Int, customerId:Int) = viewModelScope.launch(Dispatchers.IO){
        staffResponseState.value = StaffEvents.Loading("Deleting Customer...")
        staffRepository.deleteCustomer(staff_id, customerId).catch {e->
            staffResponseState.value = StaffEvents.Failure(e.message.toString())
        }.collect { customerList->
            staffResponseState.value = StaffEvents.DeletSuccess
        }
    }


}