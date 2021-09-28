package org.bedu.kolorweatherbedu.Extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject

//operator fun JSONArray.iterator():Iterator<JSONObject> =(0 until this.length()).asSequence().map { this.get(it) as JSONObject }.iterator()
operator fun JSONArray.iterator():Iterator<JSONObject> =(0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

fun ViewGroup.inflate(resource:Int): View = LayoutInflater.from(context).inflate(resource,this,false)

fun View.displaySnack(message:String, length:Int= Snackbar.LENGTH_LONG, func: Snackbar.()->Unit){
    val snackBar= Snackbar.make(this,message,length)
    snackBar.func()
    snackBar.show()
}

fun Snackbar.action(message: String, listener:(View)->Unit){
    setAction(message,listener)
}