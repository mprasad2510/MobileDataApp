package com.android.myapplication.data

import com.android.myapplication.model.RecordsItem


interface OperationCallback<T> {
    fun onSuccess(data: List<RecordsItem>?)
    fun onError(error: String?)
}