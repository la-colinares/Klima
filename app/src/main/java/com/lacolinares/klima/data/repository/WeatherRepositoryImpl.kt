package com.lacolinares.klima.data.repository

import com.lacolinares.klima.data.mapper.toDomain
import com.lacolinares.klima.data.remote.api.OpenWeatherApi
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: OpenWeatherApi,
    private val apiKey: String,
): WeatherRepository {

    override suspend fun getWeather(lat: Double, lon: Double): WeatherInfo {
        val responseDto = weatherApi.getWeather(
            lat = lat,
            lon = lon,
            units = "metric",
            apiKey = apiKey
        )
        return responseDto.toDomain()
    }
}