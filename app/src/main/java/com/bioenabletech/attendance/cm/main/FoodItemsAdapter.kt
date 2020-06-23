package com.bioenabletech.attendance.cm.main

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.database.FoodItem

class FoodItemsAdapter() : RecyclerView.Adapter<FoodItemsAdapter.Holder>() {

    private val TAG = FoodItemsAdapter::class.java.simpleName
    private lateinit var foodItems : List<FoodItem>
    private lateinit var counts: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_food_item,parent,false))
    }

    override fun getItemCount(): Int {
        return if(::foodItems.isInitialized) foodItems.size else 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(foodItems[position])
    }

    fun setFoodItems(items: List<FoodItem>){
        this.foodItems = items
        counts = Array(items.size){0}
        Log.e(TAG,"setFoodItems: ${counts.size}")
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var count = 0
        private lateinit var name :String
        private   var id: Int = 0

        private val tvName = itemView.findViewById<TextView>(R.id.tv_food_item_name)

        private val tvCount = itemView.findViewById<TextView>(R.id.tv_item_count)

        private val tvPrize = itemView.findViewById<TextView>(R.id.tv_food_item_prize)

        init {
            tvCount.text = "$count"
            val btnInc = itemView.findViewById<ImageView>(R.id.iv_count_increase)
            val btnDec = itemView.findViewById<ImageView>(R.id.iv_count_decrease)

            btnInc.setOnClickListener {
                count+=1
                tvCount.text = "$count"
                counts[adapterPosition] = count
                updateItemIncluded()
            }

            btnDec.setOnClickListener {
                if(count>0){
                    count-=1
                    tvCount.text = "$count"
                    counts[adapterPosition] = count
                    updateItemIncluded()
                }
            }

            itemView.setOnClickListener{
                count+=1
                tvCount.text = "$count"
                counts[adapterPosition] = count
                updateItemIncluded()
            }
        }

        fun setData(item: FoodItem){
            tvName.text = item.itemName
            tvCount.text = "${counts[adapterPosition]}"
            tvPrize.text = "Rs. ${item.price}.00"
            name = item.itemName
            id = item.id
            updateItemIncluded()
        }

        private fun updateItemIncluded() {
            val count = counts[adapterPosition]
            tvName.setBackgroundColor(if(count>0) Color.LTGRAY else Color.TRANSPARENT)
            tvPrize.setBackgroundColor(if(count>0) Color.LTGRAY else Color.TRANSPARENT)
        }
    }

    fun getFoodSelection() : List<FoodItemWithCount> {
        val items = ArrayList<FoodItemWithCount>()
        Log.e(TAG,"getFoodSelection: ${this.counts}")
        for((index,count) in counts.iterator().withIndex()) {
            if(count>0){
                val item = foodItems[index]
                items.add(FoodItemWithCount(item.id, item.itemName, item.price, count))
            }
        }
        return items
    }

    class FoodItemWithCount(val id:Int, val name:String, val price:Int, val count:Int)
}