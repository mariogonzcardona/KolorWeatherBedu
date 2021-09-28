package org.bedu.kolorweatherbedu.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.bedu.kolorweatherbedu.Extensions.inflate
import org.bedu.kolorweatherbedu.Models.Day
import org.bedu.kolorweatherbedu.R

class DayAdapter(val context: Context, val dataSource:ArrayList<Day>): BaseAdapter(){
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    //Primer elemento: Position, vista actual, vista padre
    override fun getView(position: Int, currentView: View?, parentView: ViewGroup): View {
        val viewHolder: ViewHolder
        val view:View

        if(currentView == null) {
            //Hijo
            view = parentView.inflate(R.layout.daily_item)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            viewHolder= currentView?.tag as ViewHolder
            view=currentView
        }
        val currentDay=dataSource[position]
        viewHolder.apply {
            dayTextView.text=currentDay.getFormattedTime()
            minTextView.text="Min ${currentDay.minTemp.toInt()} C"
            maxTextView.text="Min ${currentDay.maxTemp.toInt()} C"
        }
        return view
    }

    private class ViewHolder(view:View){
        val dayTextView: TextView =view.findViewById(R.id.dayTextView)
        val minTextView:TextView=view.findViewById(R.id.minTextView)
        val maxTextView:TextView=view.findViewById(R.id.maxTextView)
    }
}