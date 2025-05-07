package com.lacolinares.klima.presensation.screens.main.weather

import com.lacolinares.klima.domain.model.WeatherInfo

data class WeatherListUiState(
    val isLoading: Boolean = false,
    val weathers: List<WeatherInfo> = emptyList(),
    val error: String = ""
)