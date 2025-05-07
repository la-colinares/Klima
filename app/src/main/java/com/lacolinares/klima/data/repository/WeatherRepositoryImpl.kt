package com.lacolinares.klima.data.repository

import com.lacolinares.klima.data.local.database.dao.WeatherDao
import com.lacolinares.klima.data.local.database.entities.WeatherEntity
import com.lacolinares.klima.data.mapper.toDomain
import com.lacolinares.klima.data.remote.api.OpenWeatherApi
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val weatherApi: OpenWeatherApi,
    private val weatherDao: WeatherDao,
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

    override suspend fun saveWeather(weatherEntity: WeatherEntity) {
        weatherDao.insert(weatherEntity)
    }

    override fun getWeathers(): Flow<List<WeatherEntity>> {
        return weatherDao.loadWeathers()
    }
}