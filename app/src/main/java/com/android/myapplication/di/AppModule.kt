package com.android.myapplication.di

import androidx.lifecycle.ViewModelProvider
import com.android.myapplication.data.ApiClient
import com.android.myapplication.data.MobileDataRemoteDataSource
import com.android.myapplication.model.MobileDataSource
import com.android.myapplication.model.MobileDataRepository
import com.android.myapplication.viewmodel.ViewModelFactory

object AppModule {

    private val dataSource: MobileDataSource = MobileDataRemoteDataSource(ApiClient)
    private val repository = MobileDataRepository(dataSource)
    private val viewModelFactory = ViewModelFactory(repository)


    fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

}