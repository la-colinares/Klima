package com.lacolinares.klima.domain.usecase.weather

import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveWeatherUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val saveWeatherUseCase = SaveWeatherUseCase(weatherRepository)

    @Test
    fun `should convert WeatherInfo to entity and save it`() = runTest {
        // Given
        val weatherInfo = mockk<WeatherInfo>(relaxed = true)

        coEvery { weatherRepository.saveWeather(any()) } just Runs

        // When
        saveWeatherUseCase.execute(weatherInfo)

        // Then
        coVerify(exactly = 1) { weatherRepository.saveWeather(any()) }
    }
}