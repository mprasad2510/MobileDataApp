package com.android.myapplication.model

import com.android.myapplication.data.OperationCallback


class MobileDataRepository (private val mobileDataSource: MobileDataSource) {

    fun fetchRecords(callback: OperationCallback<Response>) =
        mobileDataSource.retrieveRecords(callback)

}