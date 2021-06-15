package com.android.myapplication.di

import androidx.lifecycle.ViewModelProvider
import com.android.myapplication.data.ApiClient
import com.android.myapplication.data.MobileDataRemoteDataSource
import com.android.myapplication.model.MobileDataSource
import com.android.myapplication.model.MobileDataRepository
import com.android.myapplication.viewmodel.ViewModelFactory


object Injection {

    private val museumDataSource: MobileDataSource = MobileDataRemoteDataSource(ApiClient)
    private val museumRepository = MobileDataRepository(museumDataSource)
    private val museumViewModelFactory = ViewModelFactory(museumRepository)

    fun providerRepository(): MobileDataSource {
        return museumDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return museumViewModelFactory
    }
}