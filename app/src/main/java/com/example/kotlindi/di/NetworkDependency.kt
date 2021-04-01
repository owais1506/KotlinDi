package com.example.kotlindi.di

import com.example.kotlindi.Singleton
import com.example.kotlindi.data.network.CallApi
import com.example.kotlindi.data.repo.OrderRepo
import com.example.kotlindi.ui.viewmodel.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkDependency = module {
    single {
        Singleton.createRetrofitClient().create(CallApi::class.java)
    }
    single {
        OrderRepo(get())
    }
}

val viewmodelModule = module {
    viewModel {
        OrderViewModel(get())
    }
}