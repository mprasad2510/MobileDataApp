package com.android.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.myapplication.model.MobileDataRepository


class ViewModelFactory(private val repository: MobileDataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MobileDataViewModel(repository) as T
    }
}