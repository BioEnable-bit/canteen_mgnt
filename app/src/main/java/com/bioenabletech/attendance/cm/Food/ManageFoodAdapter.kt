package com.bioenabletech.attendance.cm.Food

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bioenabletech.attendance.cm.R
import com.bioenabletech.attendance.cm.database.FoodItem

class ManageFoodAdapter(context: Context,onClickToDelete: onClick) :RecyclerView.Adapter<ManageFoodAdapter.Holder>(){

    interface onClick
    {
        fun onDelete(id:Int,name:String)
    }

    private lateinit var onClickToDelete : onClick

    private lateinit var foodItems : List<FoodItem>
    private lateinit var counts : Array<Int>
    private lateinit var context : Context

    init {
        this.context=context;
        this.onClickToDelete=onClickToDelete
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageFoodAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_manage_food_item,parent,false))
     }

    override fun getItemCount(): Int {
        return if(::foodItems.isInitialized) foodItems.size else 0
    }

    override fun onBindViewHolder(holder: ManageFoodAdapter.Holder, position: Int) {
        holder.setData(foodItems[position])
        holder.getData(foodItems[position])
        holder.deleteDate(foodItems[position])



    }



    fun setFoodItems(items: List<FoodItem>){
        this.foodItems = items
        counts = Array(items.size){0}
        notifyDataSetChanged()
    }

    inner class Holder(itemview : View) : RecyclerView.ViewHolder(itemview){


        private var count = 0

        private val tvName = itemView.findViewById<TextView>(R.id.tv_food_item_name)
        private val tvPrize = itemView.findViewById<TextView>(R.id.tv_food_item_prize)
        private val ivEdit = itemview.findViewById<ImageView>(R.id.iv_edit)
        private val ivDelete = itemview.findViewById<ImageView>(R.id.iv_delete)


        fun getData(item: FoodItem)
        {
            ivEdit.setOnClickListener{

                val intent = Intent(context,AddFoodActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id",item.id)
                bundle.putString("name",item.itemName)
                bundle.putInt("price",item.price)
                intent.putExtras(bundle)
                context.startActivity(intent)

            }
        }

        fun deleteDate(item: FoodItem)
        {
            ivDelete.setOnClickListener{

                onClickToDelete.onDelete(item.id,item.itemName)




        }
        }

        fun setData(item: FoodItem ){


            tvName.text = item.itemName
            tvPrize.text = "Rs. ${item.price}.00"
            updateItemIncluded()
        }

        private fun updateItemIncluded(){
            val count = counts[adapterPosition]
            tvName.setBackgroundColor(if(count>0) Color.LTGRAY else Color.TRANSPARENT)
            tvPrize.setBackgroundColor(if(count>0) Color.LTGRAY else Color.TRANSPARENT)
        }

    }


}