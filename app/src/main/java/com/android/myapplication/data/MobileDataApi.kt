package com.android.myapplication.data

import com.android.myapplication.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MobileDataApi {

    @GET("api/action/datastore_search")
    fun mobileData(@Query("resource_id")resource_id:String, @Query("limit") limit: Int): Call<Response>
}