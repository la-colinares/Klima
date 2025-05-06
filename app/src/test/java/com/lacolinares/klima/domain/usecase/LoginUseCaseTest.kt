package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.data.local.database.entities.UserEntity
import com.lacolinares.klima.domain.repository.UserRepository
import com.lacolinares.klima.domain.validation.FieldType
import com.lacolinares.klima.util.SecurityUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LoginUseCaseTest {

    private val userRepository = mockk<UserRepository>()
    private val useCase = LoginUseCase(userRepository)

    @Test
    fun `should return field error when email is blank`() = runTest {
        val result = useCase.execute(email = "", password = "password123")

        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Email is required", result.fieldErrors[FieldType.EMAIL])
    }

    @Test
    fun `should return field error when password is blank`() = runTest {
        // When
        val result = useCase.execute(email = "test@email.com", password = "")

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Password is required", result.fieldErrors?.get(FieldType.PASSWORD))
    }

    @Test
    fun `should return error when email is invalid`() = runTest {
        // When
        val result = useCase.execute(email = "invalidemail", password = "password123")

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Invalid Email Address", result.fieldErrors[FieldType.EMAIL])
    }

    @Test
    fun `should return invalid credentials when user is not found`() = runTest {
        // When
        coEvery { userRepository.findByEmail("test@email.com") } returns null

        // Then
        val result = useCase.execute("test@email.com", "password123")
        assert(result.success.not())
        assertEquals("Invalid username or password", result.errorMessage)
    }

    @Test
    fun `should return success when credentials are valid`() = runTest {
        val plainPassword = "password123"
        val hashedPassword = SecurityUtils.hashPassword(plainPassword)
        val user = UserEntity(fullName = "John Doe", email = "test@email.com", password = hashedPassword)

        coEvery { userRepository.findByEmail(user.email) } returns user

        val result = useCase.execute(user.email, plainPassword)
        assert(result.success)
    }

    @Test
    fun `should return error result on exception`() = runTest {
        coEvery { userRepository.findByEmail(any()) } throws RuntimeException("Unexpected error")

        val result = useCase.execute("test@email.com", "password123")
        assert(result.success.not())
        assertEquals("Error occurred. Please try again.", result.errorMessage)
    }


}