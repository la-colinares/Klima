package com.lacolinares.klima.data.remote.api

import com.lacolinares.klima.data.remote.dto.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): WeatherDTO

}