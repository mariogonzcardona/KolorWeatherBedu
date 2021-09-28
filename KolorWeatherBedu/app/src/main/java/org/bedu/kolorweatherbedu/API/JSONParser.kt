package org.bedu.kolorweatherbedu.API

import org.bedu.kolorweatherbedu.Extensions.iterator
import org.bedu.kolorweatherbedu.Models.CurrentWeather
import org.bedu.kolorweatherbedu.Models.Day
import org.bedu.kolorweatherbedu.Models.Hour
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class JSONParser {
    fun getCurrentWeatherFromJson(response:JSONObject):CurrentWeather{
        val currentJSON=response.getJSONObject(currently)
        with(currentJSON){
            val currentWeather=CurrentWeather(
                icon = getString(icon),
                summary = getString(summary),
                temp = getDouble(temperature),
                precip = getDouble(precipProbability)
            )
            return currentWeather
        }
    }
    //Sin iterador
//    fun getDailyWeather(response:JSONObject):ArrayList<Day>{
//        val dailyJSON=response.getJSONObject(daily)
//        val dayJSONArray=dailyJSON.getJSONArray(data)
//        val days=ArrayList<Day>()
//
//        for (i in 0..dailyJSON.length()){
//            val dayJSONObject=dayJSONArray.getJSONObject(i)
//            val minTemp=dayJSONObject.getDouble(temperatureMin)
//            val maxTemp=dayJSONObject.getDouble(temperatureMax)
//            val time=dayJSONObject.getLong(time)
//            days.add(Day(time,minTemp,maxTemp))
//        }
//        return days
//    }

    //Con iterador
    fun getDailyWeatherFromJson(response: JSONObject):ArrayList<Day>{
        val dailyJSON=response.getJSONObject(daily)
        val timeZone=response.getString(timeZone)
        val dayJSONArray=dailyJSON.getJSONArray(data)
        val days=ArrayList<Day>()

        for(jsonDay in dayJSONArray){
            val minTemp=jsonDay.getDouble(temperatureMin)
            val maxTemp=jsonDay.getDouble(temperatureMax)
            val time=jsonDay.getLong(time)
            days.add(Day(time,minTemp,maxTemp,timeZone))
        }
        return days
    }

    fun getHourlyWeatherFromJson(response: JSONObject):ArrayList<Hour>{
        val hourlyJSON=response.getJSONObject(hourly)
        val timeZone=response.getString(timeZone)
        val hourJSONArray=hourlyJSON.getJSONArray(data)
        val hours=ArrayList<Hour>()

        for(jsonHour in hourJSONArray){
            val time =jsonHour.getLong(time)
            val temperature=jsonHour.getDouble(temperature)
            val precipProb=jsonHour.getDouble(precipProbability)
            hours.add(Hour(time, temperature, precipProb,timeZone))
        }
        return hours
    }



    fun formatCurrentTime(response:JSONObject):String{
        val currentJSON=response.getJSONObject(currently)
        with(currentJSON){
            val date: Date = Date(getLong(time) * 1000L)
            val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy")
            sdf.timeZone = TimeZone.getTimeZone(timeZone)
            return sdf.format(date)
        }
    }
}