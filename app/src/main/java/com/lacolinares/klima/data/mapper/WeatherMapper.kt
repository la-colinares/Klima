package com.lacolinares.klima.data.mapper

import com.lacolinares.klima.data.local.database.entities.WeatherEntity
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

fun WeatherEntity.toDomain(): WeatherInfo {
    return WeatherInfo(
        id = id,
        weatherDateTime = weatherDateTime,
        city = city,
        country = country,
        temperature = temperature,
        sunriseTime = sunriseTime,
        sunsetTime = sunsetTime,
        weatherName = weatherName
    )
}

fun WeatherInfo.toEntity(): WeatherEntity {
    return WeatherEntity(
        weatherDateTime = weatherDateTime,
        city = city,
        country = country,
        temperature = temperature,
        sunriseTime = sunriseTime,
        sunsetTime = sunsetTime,
        weatherName = weatherName
    )
}