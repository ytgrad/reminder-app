package com.example.reminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.time.LocalDate
import java.time.LocalTime

class MyAdapter(private val dataList: MutableList<MutableMap<String, Any>>): Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.tvTitle.text = item["title"].toString()
        holder.tvDesc.text = item["desc"].toString()

        val reminderTime = item["time"] as? LocalTime ?: LocalTime.now()
        val amOrPm = if (reminderTime.hour > 11) "pm" else "am"
        val hourToShow = if (reminderTime.hour > 12) reminderTime.hour - 12 else reminderTime.hour
        holder.tvTime.text = String.format("%02d:%02d  %s", hourToShow, reminderTime.minute, amOrPm)

        val reminderDate = item["date"] as? LocalDate ?: LocalDate.now()
        holder.tvDayAndMonth.text = String.format("%02d %s", reminderDate.dayOfMonth, reminderDate.month.toString().lowercase().replaceFirstChar { a -> a.uppercase() })
        holder.tvYear.text = reminderDate.year.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View): ViewHolder(itemView){
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
        var tvDayAndMonth = itemView.findViewById<TextView>(R.id.tv_day_and_month)
        var tvYear = itemView.findViewById<TextView>(R.id.tv_year)
    }
}