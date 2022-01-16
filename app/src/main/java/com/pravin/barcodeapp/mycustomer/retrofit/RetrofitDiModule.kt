package com.pravin.barcodeapp.mycustomer.retrofit

import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.service.AdminEndpoints
import com.pravin.barcodeapp.mycustomer.service.BaseDataApiEndPoints
import com.pravin.barcodeapp.mycustomer.service.StaffEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitDiModule {

    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getBaseDataService(retrofit: Retrofit):BaseDataApiEndPoints{
        return retrofit.create(BaseDataApiEndPoints::class.java)
    }
    @Singleton
    @Provides
    fun getAdminService(retrofit: Retrofit):AdminEndpoints{
        return retrofit.create(AdminEndpoints::class.java)
    }
    @Singleton
    @Provides
    fun getStaffService(retrofit: Retrofit):StaffEndpoints{
        return retrofit.create(StaffEndpoints::class.java)
    }

}