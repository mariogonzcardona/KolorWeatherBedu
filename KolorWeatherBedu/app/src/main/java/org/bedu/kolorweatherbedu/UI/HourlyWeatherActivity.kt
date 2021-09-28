package org.bedu.kolorweatherbedu.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hourly_weather.*
import org.bedu.kolorweatherbedu.Adapters.HourlyAdapter
import org.bedu.kolorweatherbedu.Models.Hour
import org.bedu.kolorweatherbedu.R

class HourlyWeatherActivity : AppCompatActivity() {

    //Crear la clase Hour X
    //Modificar JSONParser para obtener Hour X
    //Hacer HourlyWeather Parcelable X
    //Mandar un arreglo de horas de MainActivity a HourlyWeatherActivity X
    //Crear nuestro Adapter HourAdapter X
    //Desplegar informacion en nuestro UI X

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)

        hourlyRecyclerView.layoutManager= LinearLayoutManager(this)

        intent.let {
            val hours:ArrayList<Hour> =it.getParcelableArrayListExtra(MainActivity.HOURLY_WEATHER)!!
            hourlyRecyclerView.adapter= HourlyAdapter(hours)

            if (hours.isEmpty()) {
                hourlyRecyclerView.visibility = View.GONE;
                emptyTextView.visibility = View.VISIBLE;
            }
            else {
                hourlyRecyclerView.visibility = View.VISIBLE;
                hourlyRecyclerView.adapter= HourlyAdapter(hours)
                emptyTextView.visibility = View.GONE;
            }
        }
    }
}