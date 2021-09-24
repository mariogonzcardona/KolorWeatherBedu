package org.bedu.kolorweatherbedu.API

import org.bedu.kolorweatherbedu.Models.CurrentWeather
import org.json.JSONObject

class JSONParser {
    fun getCurrentWeatherFromJson(response:JSONObject):CurrentWeather{
        val currentJSON=response.getJSONObject(currently)
        with(currentJSON){
            val currentWeather=CurrentWeather(
                icon = getString(icon),
                summary = getString(summary),
                temp = getDouble(temperature),
                precip = getDouble(precipProbability)
//                time = formatCurrentTime(getLong("time"))
            )
            return currentWeather
        }
    }
}