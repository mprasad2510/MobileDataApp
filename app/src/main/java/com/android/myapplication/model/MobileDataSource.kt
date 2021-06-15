package com.android.myapplication.model

import com.android.myapplication.data.OperationCallback


interface MobileDataSource {

    fun retrieveRecords(callback: OperationCallback<Response>)
    fun cancel()
}