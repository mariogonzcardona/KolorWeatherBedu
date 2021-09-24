package org.bedu.kolorweatherbedu.Models

import java.text.SimpleDateFormat
import java.util.*

class Day (val time:Long, val minTemp:Double, val maxTemp:Double) {

    fun getFormattedTime():String{
        val formatter= SimpleDateFormat("EEEE", Locale.US)
        val dayOfWeek= Date(time*1000)
        return formatter.format(dayOfWeek)
    }
}