package com.lacolinares.klima.data.mapper

import com.lacolinares.klima.data.remote.dto.WeatherDTO
import com.lacolinares.klima.domain.model.WeatherInfo

fun WeatherDTO.toDomain(): WeatherInfo {
    return WeatherInfo(
        weatherDateTime = dateTime,
        city = city,
        country = sys.country,
        temperature = main.temp,
        sunriseTime = sys.sunrise,
        sunsetTime = sys.sunset,
        weatherName = weather.firstOrNull()?.main.orEmpty()
    )
}