package com.pravin.barcodeapp.mycustomer.database

import android.content.Context
import androidx.room.Room
import com.pravin.barcodeapp.mycustomer.Util.Constants
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
        ).allowMainThreadQueries().build()
    }
}