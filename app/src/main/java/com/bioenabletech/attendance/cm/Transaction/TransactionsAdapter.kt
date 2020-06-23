package com.bioenabletech.attendance.cm.Transaction

 import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.database.Transaction
 import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter() : RecyclerView.Adapter<TransactionsAdapter.Holder>() {

    private lateinit var transaction : List<Transaction>
    private val TAG = TransactionsAdapter::class.java.simpleName

    private lateinit var counts: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction,parent,false))

    }

    override fun getItemCount(): Int {
        return if(::transaction.isInitialized) transaction.size else 0
     }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TransactionsAdapter.Holder, position: Int) {
        holder.setData(transaction[position])

    }
    fun setTrasaction(items: List<Transaction>){
        this.transaction = items
        counts = Array(items.size){0}
        Log.e(TAG,"setFoodItems: ${counts.size}")
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var count = 0
        private lateinit var name :String
        private   var id: Int = 0

        private val tvuuid = itemView.findViewById<TextView>(R.id.tv_uuid)

        private val tvtotal_amount = itemView.findViewById<TextView>(R.id.tv_total_amount)

        private var tvdate_time = itemView.findViewById<TextView>(R.id.tv_date_time)


        @RequiresApi(Build.VERSION_CODES.O)
        fun setData(item: Transaction)
        {
            val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
            val netDate = Date(item.timestamp)
            tvuuid.text = "UID : ${item.uid}"
            tvdate_time.text = sdf.format(netDate)
            tvtotal_amount.text = "Rs. ${item.amount}.00"
        }


    }}