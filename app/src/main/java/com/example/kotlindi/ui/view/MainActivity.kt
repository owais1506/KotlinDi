package com.example.kotlindi.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindi.BaseActivity
import com.example.kotlindi.BaseActivity.Companion.getInstance
import com.example.kotlindi.R
import com.example.kotlindi.ui.adapter.OrderAdapter
import com.example.kotlindi.ui.viewmodel.OrderViewModel
import com.example.kotlindi.util.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val orderViewModel : OrderViewModel by viewModel()
    private lateinit var adapter: OrderAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var error : TextView
    private lateinit var progress : ContentLoadingProgressBar
    private var baseActivity = BaseActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseActivity = getInstance()
        orderViewModel.getReposFromGitHub()
        recyclerView = findViewById(R.id.manageBillRecycler)
        error = findViewById(R.id.error)
        progress = findViewById(R.id.progressBar)
        adapter = OrderAdapter()
        recyclerView.adapter = adapter
        viewmodelConnections()

    }

    private fun viewmodelConnections(){
        orderViewModel.observeData().observe(this, Observer {
            when(it){
                is Result.Success ->{
                    Log.e("In Success","Call")
                    Log.e("Data",it.data.order.toString())
                    adapter.setList(it.data.order)
                    error.visibility = View.GONE
                    progress.hide()
                }


                is Result.Error.RecoverableError ->{
                    progress.hide()
                    error.text = it.exception.message
                }

                is Result.Error.NonRecoverableError ->{
                    progress.hide()
                    error.text = it.exception.message
                }

                is Result.Loading ->{
                    error.visibility = View.GONE
                    if(it.status){
                        progress.show()
                    }else
                        progress.hide()
                }

            }
        })
    }
}