package com.android.myapplication.data


import com.android.myapplication.model.MobileDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MobileDataRemoteDataSource(apiClient: ApiClient) : MobileDataSource {

    private var call: Call<com.android.myapplication.model.Response>? = null
    private val service = apiClient.build()

    override fun retrieveRecords(callback: OperationCallback<com.android.myapplication.model.Response>) {

        call = service?.mobileData("a807b7ab-6cad-4aa6-87d0-e283a7353a0f",59)
        call?.enqueue(object : Callback<com.android.myapplication.model.Response> {
            override fun onFailure(call: Call<com.android.myapplication.model.Response>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<com.android.myapplication.model.Response>,
                response: Response<com.android.myapplication.model.Response>
            ) {
                response.body()?.let {
                    if (it.success && !it.result?.records.isNullOrEmpty()) {
                        callback.onSuccess(it.result?.records)
                    } else {
                        callback.onError(it.result?.resourceId)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}