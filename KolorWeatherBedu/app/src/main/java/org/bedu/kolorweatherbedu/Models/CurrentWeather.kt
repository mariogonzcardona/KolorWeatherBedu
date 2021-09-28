package org.bedu.kolorweatherbedu.Models

import org.bedu.kolorweatherbedu.R

data class CurrentWeather(var icon:String,var summary:String,var temp:Double,var precip:Double) {

    fun getIconResource():Int{
        return when (icon) {
            "clear-night" -> R.drawable.clear_night
            "clear-day" -> R.drawable.clear_day
            "cloudy" -> R.drawable.cloudy
            "cloudy-night" -> R.drawable.cloudy_night
            "fog" -> R.drawable.fog
            "partly-cloudy" -> R.drawable.partly_cloudy
            "partly-cloudy-night" -> R.drawable.cloudy_night
            "partly-cloudy-day" -> R.drawable.cloudy
            "rain" -> R.drawable.rain
            "sleet" -> R.drawable.sleet
            "snow" -> R.drawable.snow
            "sunny" -> R.drawable.snow
            "wind" -> R.drawable.wind
            else -> R.drawable.na
        }
    }
}