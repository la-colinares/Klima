package com.lacolinares.klima.domain.usecase.weather

import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(lat: Double, lon: Double): WeatherInfo {
        return weatherRepository.getWeather(lat, lon)
    }

}