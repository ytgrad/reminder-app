package com.example.reminder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.time.LocalDate
import java.time.LocalTime


class MyAdapter: ListAdapter<ItemType, MyAdapter.MyViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(view: View):ViewHolder(view){
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
        var tvDayAndMonth = itemView.findViewById<TextView>(R.id.tv_day_and_month)
        var tvYear = itemView.findViewById<TextView>(R.id.tv_year)

        fun bind(item: ItemType){
            tvTitle.text = item.title
            tvDesc.text = item.desc
            val amOrPm = if(item.time.hour > 11 ) "pm" else "am"
            var hour = 0
            if (item.time.hour > 12){
                hour = item.time.hour - 12
            }else{
                hour = item.time.hour
            }
            tvTime.text = String.format("%02d:%02d %s", hour, item.time.minute, amOrPm)
//            tvTime.text = item.time.toString().slice(0..4)
            val dayAndMonth = "${item.dayOfMonth} ${item.month.toString().slice(0..2).lowercase().replaceFirstChar { a -> a.uppercase() }}"
            tvDayAndMonth.text = dayAndMonth
            tvYear.text = item.year.toString()
        }
    }


    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<ItemType>(){
        override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
            return oldItem == newItem
        }

    }
}


















//class MyAdapter(private val dataList: MutableList<MutableMap<String, Any>>): Adapter<MyAdapter.MyViewHolder>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_layout, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val item = dataList[position]
//        holder.tvTitle.text = item["title"].toString()
//        holder.tvDesc.text = item["desc"].toString()
//
//        val reminderTime = item["time"] as? LocalTime ?: LocalTime.now()
//        val amOrPm = if (reminderTime.hour > 11) "pm" else "am"
//        val hourToShow = if (reminderTime.hour > 12) reminderTime.hour - 12 else reminderTime.hour
//        holder.tvTime.text = String.format("%02d:%02d  %s", hourToShow, reminderTime.minute, amOrPm)
//
//        val reminderDate = item["date"] as? LocalDate ?: LocalDate.now()
//        holder.tvDayAndMonth.text = String.format("%02d %s", reminderDate.dayOfMonth, reminderDate.month.toString().lowercase().replaceFirstChar { a -> a.uppercase() })
//        holder.tvYear.text = reminderDate.year.toString()
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    class MyViewHolder(itemView: View): ViewHolder(itemView){
//        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
//        var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
//        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
//        var tvDayAndMonth = itemView.findViewById<TextView>(R.id.tv_day_and_month)
//        var tvYear = itemView.findViewById<TextView>(R.id.tv_year)
//    }
//}