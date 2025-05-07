package com.lacolinares.klima.domain.usecase.weather

import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetWeatherUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val getWeatherUseCase = GetWeatherUseCase(weatherRepository)

    @Test
    fun `should return WeatherInfo from repository`() = runTest {
        // Given
        val lat = 14.5995
        val lon = 120.9842
        val expectedWeatherInfo = mockk<WeatherInfo>()
        coEvery { weatherRepository.getWeather(lat, lon) } returns expectedWeatherInfo

        // When
        val result = getWeatherUseCase.execute(lat, lon)

        // Then
        assertEquals(expectedWeatherInfo, result)
        coVerify(exactly = 1) { weatherRepository.getWeather(lat, lon) }
    }
}