package com.lacolinares.klima.domain.usecase.weather

import com.lacolinares.klima.data.mapper.toDomain
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeathersUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(): Flow<List<WeatherInfo>> {
        return weatherRepository.getWeathers().map { entities -> entities.map { it.toDomain() } }
    }

}