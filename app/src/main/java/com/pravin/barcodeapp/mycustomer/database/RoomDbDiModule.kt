package com.pravin.barcodeapp.mycustomer.database

import android.content.Context
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.Util.Constants
import com.pravin.barcodeapp.mycustomer.dao.CustomerDao
import com.pravin.barcodeapp.mycustomer.dao.GenderDao
import com.pravin.barcodeapp.mycustomer.dao.StatusDao
import com.pravin.barcodeapp.mycustomer.dao.TypeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDbDiModule {

    @Provides
    @Singleton
    fun getRoomDataBase(@ApplicationContext context: Context):RoomDb{
        return  Room.databaseBuilder(context, RoomDb::class.java, Constants.DbName
        ).build()
    }

    @Provides
    @Singleton
    fun getGenderDao(roomDb: RoomDb):GenderDao = roomDb.genderDao()

    @Provides
    @Singleton
    fun getStatusDao(roomDb: RoomDb):StatusDao = roomDb.statusDao()

    @Provides
    @Singleton
    fun getTypeDao(roomDb: RoomDb):TypeDao = roomDb.typeDao()

    @Provides
    @Singleton
    fun getCustomerDao(roomDb: RoomDb):CustomerDao = roomDb.customerDao()
}