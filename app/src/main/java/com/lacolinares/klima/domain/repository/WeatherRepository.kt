package com.lacolinares.klima.domain.repository

import com.lacolinares.klima.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherInfo
}