package com.lacolinares.klima.domain.usecase.weather

import com.lacolinares.klima.data.mapper.toEntity
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository

class SaveWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(weatherInfo: WeatherInfo) {
        val weatherEntity = weatherInfo.toEntity()
        return weatherRepository.saveWeather(weatherEntity)
    }

}