package com.example.kotlindi.data.network

import com.example.kotlindi.data.model.CustomerBills
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CallApi {
    @FormUrlEncoded
    @POST("Sync/invoice_page")
    fun getPaginates(@Field("store_id") id: String, @Field("date") date:String, @Field("invoice_type") invoice:String) : Call<CustomerBills>
}