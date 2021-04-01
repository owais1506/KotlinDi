package com.example.kotlindi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindi.R
import com.example.kotlindi.data.model.CustomerBills
import com.example.kotlindi.data.model.CustomerOrder

class OrderAdapter(val list: List<CustomerOrder> = listOf()) : RecyclerView.Adapter<MyItemHolder>() {

    var orders = list

    fun setList(new_list : List<CustomerOrder>){
        orders = new_list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemHolder {
        return MyItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyItemHolder, position: Int) {
        holder.bind(orders.get(position))
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}


class MyItemHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    var invoice = itemView.findViewById<TextView>(R.id.tv_invoice)

    fun bind(order : CustomerOrder){
        invoice.text = order.invoiceNumber
    }
}