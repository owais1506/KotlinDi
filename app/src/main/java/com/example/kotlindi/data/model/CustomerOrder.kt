package com.example.kotlindi.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CustomerOrder (

    @SerializedName(value = "invoiceNumber", alternate = ["invoice_no"])
    var invoiceNumber: String = "",

    @SerializedName("customer_id")
    var customer_id: Long? = null,

    @SerializedName(value = "totalPaidAmt", alternate = ["net_amount"])
    var totalPaidAmt: Float = 0f,

    @SerializedName(value = "netAmt", alternate = ["order_amount"])
    var netAmt: Float = 0f,

    @SerializedName("discount")
    var discount: String? = null,


    @SerializedName("ServiceCharge")
    var ServiceCharge: String? = null,

    @SerializedName("delivery_address")
    var deliveryAddress: String? = null,

    @SerializedName("delivery_charges")
    var delivery_charges: Float = 0f,

    @SerializedName(value = "addedUdhaar", alternate = ["udhaar"])
    var addedUdhaar: Float = 0f,

    @SerializedName("cash")
    var cash: Float = 0f,

//not able to find
    @SerializedName("card")
    var card: Float = 0f,

//not able to find
    @SerializedName("e_wallet")
    var eWallet: Float = 0f,

    @SerializedName("order_date")
    var order_date: Date? = null,

    @SerializedName("loyalty_points_used")
    var loyalty_points_used: Int = 0,

    @SerializedName("parent_id")
    var parent_id: String? = null,

    @SerializedName(value = "previousUdhaarPaid", alternate = ["previous_udhaar_paid"])
    var previousUdhaarPaid: Float = 0f,

    @SerializedName("trasaction_type")
    var trasaction_type: Int = 0,

    @SerializedName("bill_type") //K or P
    var bill_type: Int = 0,

    @SerializedName("is_udhaar_settled")
    var isUdhaarSettled: Boolean? = null,

    @SerializedName("assign_go_user")
    var goId: Long = 0,

    @SerializedName("assign_status")
    var goStatus: Int = 0,

    @SerializedName("assign_date")
    var goUserAssignedDate: Date? = null,

    @SerializedName("delivery_sign")
    var delivery_sign: String? = null,

    @SerializedName("status")
    var status //1 means deleted, 0 means not deleted
    : Int? = null,

    @SerializedName("note1") //details
    var note1: String? = null,

    @SerializedName("note2") //additional details
    var note2: String? = null,

    @SerializedName("biller")
    var biller: String? = null,

    @SerializedName("invoiceForShowing")
    var invoiceForShowing: String? = null,


    @SerializedName("online_status")
    var onlineStatus: Int? = null,

    var isDirty: Boolean = false,

    @Transient
    var isUdharCheck: Boolean? = null,

    @Transient
    var isCheckUdharAmt: Float = 0f,

    @SerializedName("isPinelabsPayment")
    var isPinelabsPayment: Int = 0,

    @SerializedName("pinelabsInvoiceNo")
    var pinelabsInvoiceNo //reference no
    : String? = null,

    @SerializedName("pinelabsInvoiceStatus")
    var pinelabsInvoiceStatus: Int = 0,

    @SerializedName("pinelabsRoc")
    var pinelabsRoc: Int = 0,

    @SerializedName("pinelabsBatchNo")
    var pinelabsBatchNo: Int = 0,

    @SerializedName("pinelabsPaymentType")
    var pinelabsPaymentType: Int = 0

        )


