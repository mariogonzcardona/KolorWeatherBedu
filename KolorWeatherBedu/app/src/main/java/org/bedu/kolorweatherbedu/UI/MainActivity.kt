package org.bedu.kolorweatherbedu.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.bedu.kolorweatherbedu.API.API_KEY
import org.bedu.kolorweatherbedu.API.DARCK_SKY_URL
import org.bedu.kolorweatherbedu.API.JSONParser
import org.bedu.kolorweatherbedu.Models.CurrentWeather
import org.bedu.kolorweatherbedu.R
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //Variables
    val TAG= MainActivity::class.java.simpleName
    val jsonParser= JSONParser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempTextView.text=getString(R.string.temp_placeholder,0)
        precipTextView.text= getString(R.string.prcip_placeholder,0)

        getWeather()
    }

    private fun getWeather() {
        val latitud = 25.438278503440618
        val longitud = -100.97412816177837
        val language = getString(R.string.language)
        val units = getString(R.string.units)
        val url = "$DARCK_SKY_URL/$API_KEY/$latitud,$longitud?lang=$language&units=$units"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                //1.- Obtener nuestro clima actual con la clase JSONParser,
                //2.- Asignar los valores a las views adecuadas creamos
                val responseJSON = JSONObject(response)
                val currentWeather = jsonParser.getCurrentWeatherFromJson(responseJSON)

                buildCurrentWeatherUI(currentWeather)

            },
            {
                displayErrorMessage()
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun displayErrorMessage() {
        val snackbar =
            Snackbar.make(main, getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), {
                    getWeather()
                })
        snackbar.show()
    }

    private fun buildCurrentWeatherUI(currentWeather: CurrentWeather) {
        val precipProb = (currentWeather.precip * 100).toInt()
        tempTextView.text = getString(R.string.temp_placeholder, currentWeather.temp.toInt())
        precipTextView.text = getString(R.string.prcip_placeholder, precipProb)

        descriptionTextView.text = currentWeather.summary
        iconImageView.setImageDrawable(ResourcesCompat.getDrawable(resources,currentWeather.getIconResource(),null))
    }

    fun startHourlyActivity(view : View){
        val intent= Intent()
        intent.setClass(this,HourlyWeatherActivity::class.java)
        startActivity(intent)
    }
    fun startDailyActivity(view:View){
        val intent= Intent()
        intent.setClass(this,DailyWeatherActivity::class.java)
        startActivity(intent)
    }

    //Crear la clase Day
    //Modificar JSONParser para obtener Days
    //Hacer DailyWeather Parceable
    //Mandar un arreglo de dias de MainActivity a DailyWeatherActivity
    //Crear nuestro Adapter
    //Desplegar informacion en nuestro UI
}