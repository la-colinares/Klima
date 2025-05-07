package com.lacolinares.klima.presensation.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.weather.GetWeatherUseCase
import com.lacolinares.klima.domain.usecase.weather.SaveWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val weather = getWeatherUseCase.execute(lat, lon)
                saveWeatherUseCase.execute(weatherInfo = weather)

                _state.update { it.copy(isLoading = false, weatherInfo = weather) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }
}