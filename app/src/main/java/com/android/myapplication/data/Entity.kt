package com.android.myapplication.data

import com.android.myapplication.model.MobileData


data class MobileDataResponse(val status: Boolean?, val msg: String?, val data: List<MobileData>?) {
    fun isSuccess(): Boolean = (status == true)
}