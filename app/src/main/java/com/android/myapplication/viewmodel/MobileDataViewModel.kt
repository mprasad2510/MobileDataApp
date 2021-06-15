package com.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myapplication.data.OperationCallback
import com.android.myapplication.model.MobileData
import com.android.myapplication.model.MobileDataRepository
import com.android.myapplication.model.RecordsItem
import com.android.myapplication.model.Response


class MobileDataViewModel(private val repository: MobileDataRepository) : ViewModel() {

    private val _mobileData = MutableLiveData<List<RecordsItem>>().apply { value = emptyList() }
    val mobileData: LiveData<List<RecordsItem>> = _mobileData

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadRecords() {
        _isViewLoading.value = true
        repository.fetchRecords(object : OperationCallback<Response> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error
            }

            override fun onSuccess(data: List<RecordsItem>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true

                } else {
                    _mobileData.value = data
                }
            }
        })
    }

}