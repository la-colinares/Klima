package com.lacolinares.klima.domain.repository

import com.lacolinares.klima.data.local.database.entities.WeatherEntity
import com.lacolinares.klima.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherInfo
    suspend fun saveWeather(weatherEntity: WeatherEntity)
    fun getWeathers(): Flow<List<WeatherEntity>>
}