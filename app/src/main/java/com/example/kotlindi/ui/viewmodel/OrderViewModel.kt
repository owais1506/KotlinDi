package com.example.kotlindi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindi.data.model.CustomerBills
import com.example.kotlindi.data.repo.OrderRepo
import com.example.kotlindi.util.Result;

class OrderViewModel(private val orderRepo: OrderRepo) : ViewModel() {

    private val orderLiveData = MutableLiveData<Result<CustomerBills>>()

    fun observeData() : MutableLiveData<Result<CustomerBills>>{
        return orderLiveData
    }

    fun getReposFromGitHub(){
        orderRepo.getRepository(liveData = orderLiveData)
    }

}