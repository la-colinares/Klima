package com.lacolinares.klima.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    @SerialName("coord")
    val coordinates: Coordinates? = null,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    @SerialName("dt")
    val dateTime: Long,
    val sys: Sys,
    val timezone: Int? = null,
    val id: Int,
    @SerialName("name")
    val city: String,
    val cod: Int
) {
    @Serializable
    data class Coordinates(
        val lon: Double,
        val lat: Double
    )

    @Serializable
    data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )

    @Serializable
    data class Main(
        val temp: Double,
        @SerialName("feels_like")
        val feelsLike: Double,
        @SerialName("temp_min")
        val tempMin: Double,
        @SerialName("temp_max")
        val tempMax: Double,
        val pressure: Int,
        val humidity: Int,
        @SerialName("sea_level")
        val seaLevel: Int,
        @SerialName("grnd_level")
        val grndLevel: Int
    )

    @Serializable
    data class Wind(
        val speed: Double,
        val deg: Int
    )

    @Serializable
    data class Clouds(
        val all: Int
    )

    @Serializable
    data class Sys(
        val type: Int,
        val id: Int,
        val country: String,
        val sunrise: Long,
        val sunset: Long
    )
}