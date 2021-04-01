package com.example.kotlindi.data.model
import com.google.gson.annotations.SerializedName

data class CustomerBills (
    //@PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("has_live")
    var has_live: Boolean? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("success_order")
    var order: List<CustomerOrder>,

    @SerializedName("success_order_details")
    var order_details: List<List<CustomerOrderDetails>>
)