package org.bedu.kolorweatherbedu.Adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.horly_item.view.*
import org.bedu.kolorweatherbedu.Extensions.inflate
import org.bedu.kolorweatherbedu.Models.Hour
import org.bedu.kolorweatherbedu.R

class HourlyAdapter(val dataSource:ArrayList<Hour>): RecyclerView.Adapter<HourlyAdapter.HourViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(parent.inflate(R.layout.horly_item))
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        return holder.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class HourViewHolder(hourlyItemView: View):RecyclerView.ViewHolder(hourlyItemView){
        fun bind(hour:Hour)= with(itemView){
            hourTextView.text=hour.getFormattedTime()
            hourPrompTextView.text="${hour.precip.toInt()} %"
            hourTempTextView.text="${hour.temp.toInt()} C"
        }
    }
}