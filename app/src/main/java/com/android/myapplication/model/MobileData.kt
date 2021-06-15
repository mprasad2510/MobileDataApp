package com.android.myapplication.model

import java.io.Serializable


data class MobileData(val volume_of_mobile_data: Int, val quarter: String, val _id: String) : Serializable