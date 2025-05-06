package com.lacolinares.klima.presensation.screens.main.home

import com.lacolinares.klima.domain.model.WeatherInfo

data class HomeUiState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String = ""
)