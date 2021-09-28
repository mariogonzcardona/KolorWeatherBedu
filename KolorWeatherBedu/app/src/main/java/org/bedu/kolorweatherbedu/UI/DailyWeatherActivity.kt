package org.bedu.kolorweatherbedu.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_daily_weather.*
import org.bedu.kolorweatherbedu.Adapters.DayAdapter
import org.bedu.kolorweatherbedu.Models.Day
import org.bedu.kolorweatherbedu.R

class DailyWeatherActivity : AppCompatActivity() {

    //Crear la clase Day X
    //Modificar JSONParser para obtener Days X
    //Hacer DailyWeather Parceable X
    //Mandar un arreglo de dias de MainActivity a DailyWeatherActivity X
    //Crear nuestro Adapter X
    //Desplegar informacion en nuestro UI X

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {
            //Checar si llego la informacion
            //Toast.makeText(this,days.get(0).getFormattedTime(),Toast.LENGTH_LONG).show()
            //Toast.makeText(this,days.get(0).toString(),Toast.LENGTH_LONG).show()

            //Desplegar informacion para la lista
            val days:ArrayList<Day> = it.getParcelableArrayListExtra(MainActivity.DAILY_WEATHER)!!
            val baseAdapter= DayAdapter(this,days)
            dailyListView.adapter=baseAdapter
        }
        dailyListView.emptyView=emptyTextView
    }
}