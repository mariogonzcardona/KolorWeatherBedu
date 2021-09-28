package org.bedu.kolorweatherbedu.Models

import android.os.Parcel
import android.os.Parcelable
import org.bedu.kolorweatherbedu.API.timeZone
import java.text.SimpleDateFormat
import java.util.*

data class Hour (val time:Long, val temp:Double,val precip:Double,val timeZone:String?): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(temp)
        parcel.writeDouble(precip)
        parcel.writeString(timeZone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hour> {
        override fun createFromParcel(parcel: Parcel): Hour {
            return Hour(parcel)
        }

        override fun newArray(size: Int): Array<Hour?> {
            return arrayOfNulls(size)
        }
    }

    fun getFormattedTime():String{
        val formatter= SimpleDateFormat("h:mm a", Locale.US)
        formatter.timeZone= TimeZone.getTimeZone(timeZone)
        val date= Date(time*1000)
        return formatter.format(date)
    }


}