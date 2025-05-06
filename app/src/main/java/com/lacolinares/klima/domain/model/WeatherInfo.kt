package com.lacolinares.klima.domain.model

import com.lacolinares.klima.util.toDayOfWeek
import com.lacolinares.klima.util.toFormattedDate

data class WeatherInfo(
    val weatherDateTime: Long = 0L,
    val city: String = "",
    val country: String = "",
    val temperature: Double = 0.00,
    val sunriseTime: Long = 0L,
    val sunsetTime: Long = 0L,
    val weatherName: String = ""
){
    fun getFormattedLocation(): String = listOf(city, country).joinToString(", ")

    fun getFormattedCurrentDate(): String = weatherDateTime.toFormattedDate("MMMM d, yyyy")

    fun getFormattedTemperature(): String = ((temperature * 10).toInt() / 10.0).toString().plus("°C")

    // Sunrise
    fun getFormattedSunriseTime(): String = sunriseTime.toFormattedDate("hh:mm a")
    fun getFormattedSunriseDay(): String = sunriseTime.toDayOfWeek("EEEE")
    fun getFormattedSunriseDate(): String = sunriseTime.toFormattedDate("MMMM d, yyyy")

    // Sunset
    fun getFormattedSunsetTime(): String = sunsetTime.toFormattedDate("hh:mm a")
    fun getFormattedSunsetDay(): String = sunsetTime.toDayOfWeek("EEEE")
    fun getFormattedSunsetDate(): String = sunsetTime.toFormattedDate("MMMM d, yyyy")
}