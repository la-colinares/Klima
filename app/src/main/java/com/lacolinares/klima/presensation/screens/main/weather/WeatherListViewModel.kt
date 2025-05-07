package com.lacolinares.klima.presensation.screens.main.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.klima.domain.usecase.weather.GetWeathersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherListViewModel(private val getWeathersUseCase: GetWeathersUseCase): ViewModel() {

    private val _state = MutableStateFlow(WeatherListUiState())
    val state = _state.asStateFlow()

    fun loadWeathers(){
        viewModelScope.launch {
            getWeathersUseCase.execute().catch { throwable ->
                _state.update { it.copy(isLoading = false, error = throwable.message.orEmpty()) }
            }.collectLatest { weathers ->
                _state.update { it.copy(isLoading = false, weathers = weathers) }
            }
        }
    }

}