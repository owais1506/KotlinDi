package com.example.kotlindi.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindi.BaseActivity
import com.example.kotlindi.data.model.CustomerBills
import com.example.kotlindi.data.network.CallApi
import com.example.kotlindi.util.Result;
import com.example.kotlindi.util.isValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*


class OrderRepo(private val callApi: CallApi) {

    fun getRepository(liveData: MutableLiveData<Result<CustomerBills>>){
        liveData.value = Result.Loading(true)

        callApi.getPaginates("1192",BaseActivity.getInstance().getCurrentDateTime(Date())?:"","")
            .enqueue(object : Callback<CustomerBills>{
                override fun onResponse(
                    call: Call<CustomerBills>,
                    response: Response<CustomerBills>
                ) {
                    if(response.isSuccessful){
                        liveData.value = Result.Success(response.body()!!)
                    }else{
                        if(response.message().isValid()){
                            liveData.value = Result.Error.RecoverableError(Exception(response.body()?.message))
                        }
                        else
                        liveData.value = Result.Error.NonRecoverableError(Exception("Un-traceable"))
                    }

                }

                override fun onFailure(call: Call<CustomerBills>, t: Throwable) {
                    if (t.message.isValid()){
                        liveData.value = Result.Error.RecoverableError(Exception(t.message.toString()))
                    }else{
                        liveData.value = Result.Error.NonRecoverableError(Exception("Un-traceable"))
                    }
                }
            })

    }

}