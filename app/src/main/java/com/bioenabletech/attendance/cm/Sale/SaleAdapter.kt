package com.bioenabletech.attendance.cm.Sale

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.Transaction.TransactionsAdapter
import com.bioenabletech.attendance.cm.database.Sale
import com.bioenabletech.attendance.cm.database.Transaction
import java.text.SimpleDateFormat
import java.util.*

class SaleAdapter() : RecyclerView.Adapter<SaleAdapter.Holder>() {

    private lateinit var sale : List<Sale>
    private val TAG = SaleAdapter::class.java.simpleName
    private lateinit var counts: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_sales,parent,false))

    }

    override fun getItemCount(): Int {
        return if(::sale.isInitialized) sale.size else 0
    }

    override fun onBindViewHolder(holder: SaleAdapter.Holder, position: Int) {
        holder.setData(sale[position])

    }
    fun setSale(items: List<Sale>){
        this.sale = items
        counts = Array(items.size){0}
        Log.e(TAG,"setFoodItems: ${counts.size}")
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private var count = 0
        private lateinit var name :String
        private   var id: Int = 0

        private val tv_transactionId = itemView.findViewById<TextView>(R.id.tv_transactionId)

        private val tv_price = itemView.findViewById<TextView>(R.id.tv_price)

        private var tvdate_time = itemView.findViewById<TextView>(R.id.tv_date_time)

        fun setData(item: Sale)
        {
            val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
            val netDate = Date(item.timestamp)
            val total_price = item.price*item.count
            tv_transactionId.text = "Token ID : ${item.transactionId.toString()}"
            tv_price.text = "Price :${item.price} X ${item.count}  : ${total_price}"
            tvdate_time.text = sdf.format(netDate)

        }
    }


}