package com.lacolinares.klima.domain.usecase.weather

import app.cash.turbine.test
import com.lacolinares.klima.data.local.database.entities.WeatherEntity
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.domain.repository.WeatherRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetWeathersUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val getWeathersUseCase = GetWeathersUseCase(weatherRepository)

    @Test
    fun `should return list of WeatherInfo converted from entities`() = runTest {
        // Given
        val entity1 = WeatherEntity(weatherName = "Sunny")
        val entity2 = WeatherEntity(weatherName = "Cloudy")

        val expected = listOf(
            WeatherInfo(weatherName = "Sunny"),
            WeatherInfo(weatherName = "Cloudy")
        )

        every { weatherRepository.getWeathers() } returns flowOf(listOf(entity1, entity2))

        // When & Then
        getWeathersUseCase.execute().test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }
}