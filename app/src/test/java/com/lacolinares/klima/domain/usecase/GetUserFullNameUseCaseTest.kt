package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.data.local.database.entities.UserEntity
import com.lacolinares.klima.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserFullNameUseCaseTest {

    private val userRepository = mockk<UserRepository>()
    private val getUserFullNameUseCase = GetUserFullNameUseCase(userRepository)


    @Test
    fun `should return full name when user is found`() = runTest {
        // Given
        val email = "test@example.com"
        val user = UserEntity(fullName = "John Doe", email = email, password = "testpassword")
        coEvery { userRepository.findByEmail(email) } returns user

        // When
        val result = getUserFullNameUseCase.execute(email)

        // Then
        assertEquals("John Doe", result)
    }

    @Test
    fun `should return empty string when user is not found`() = runTest {
        // Given
        val email = "unknown@example.com"
        coEvery { userRepository.findByEmail(email) } returns null

        // When
        val result = getUserFullNameUseCase.execute(email)

        // Then
        assertEquals("", result)
    }
}