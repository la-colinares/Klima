package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.domain.repository.UserRepository
import com.lacolinares.klima.domain.validation.FieldType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class SignUpUseCaseTest {

    private val userRepository = mockk<UserRepository>()
    private val useCase = SignUpUseCase(userRepository)

    @Test
    fun `should return field errors when all required fields are empty`() = runTest {
        // When
        val result = useCase.execute("", "", "", "")

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Full name is required", result.fieldErrors[FieldType.FULL_NAME])
        assertEquals("Email is required", result.fieldErrors[FieldType.EMAIL])
        assertEquals("Password is required", result.fieldErrors[FieldType.PASSWORD])
        assertEquals("Confirm Password is required", result.fieldErrors[FieldType.CONFIRM_PASSWORD])
    }

    @Test
    fun `should return error when full name is blank`() = runTest {
        // When
        val result = useCase.execute(
            fullName = " ",
            email = "test@example.com",
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Full name is required", result.fieldErrors[FieldType.FULL_NAME])
    }

    @Test
    fun `should return error when full name too short`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John",
            email = "test@example.com",
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Full name must be at least 6 characters", result.fieldErrors[FieldType.FULL_NAME])
    }

    @Test
    fun `should return error when email is blank`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = " ",
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Email is required", result.fieldErrors[FieldType.EMAIL])
    }

    @Test
    fun `should return error when email is invalid`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = "invalidemail",
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Invalid Email Address", result.fieldErrors[FieldType.EMAIL])
    }

    @Test
    fun `should return error when password is blank`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = "valid@email.com",
            password = "",
            confirmPassword = ""
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Password is required", result.fieldErrors[FieldType.PASSWORD])
    }

    @Test
    fun `should return error when password is too short`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = "a@b.com",
            password = "123",
            confirmPassword = "123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Password must be at least 6 characters", result.fieldErrors[FieldType.PASSWORD])
    }

    @Test
    fun `should return error when password and confirm password do not match`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = "user@example.com",
            password = "password123",
            confirmPassword = "differentPassword"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Password do not match", result.fieldErrors[FieldType.CONFIRM_PASSWORD])
    }

    @Test
    fun `should return error when confirm password is blank`() = runTest {
        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = "valid@email.com",
            password = "password123",
            confirmPassword = ""
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Confirm Password is required", result.fieldErrors[FieldType.CONFIRM_PASSWORD])
    }

    @Test
    fun `should return error when email is already taken`() = runTest {
        // Given
        val email = "taken@example.com"
        coEvery { userRepository.isEmailTaken(email) } returns true

        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = email,
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNull(result.errorMessage)
        assertEquals("Email already exists", result.fieldErrors[FieldType.EMAIL])
    }

    @Test
    fun `should return success when all fields are valid and user is saved`() = runTest {
        // Given
        val email = "newuser@example.com"
        coEvery { userRepository.isEmailTaken(email) } returns false
        coEvery { userRepository.insertUser(any()) } returns Unit

        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = email,
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success)
        assertNull(result.errorMessage)
        coVerify { userRepository.insertUser(any()) }
    }

    @Test
    fun `should return error when an exception occurs while saving user`() = runTest {
        // Given
        val email = "newuser@example.com"
        coEvery { userRepository.isEmailTaken(email) } returns false
        coEvery { userRepository.insertUser(any()) } throws Exception("Database error")

        // When
        val result = useCase.execute(
            fullName = "John Doe",
            email = email,
            password = "password123",
            confirmPassword = "password123"
        )

        // Then
        assert(result.success.not())
        assertNotNull(result.errorMessage)
        assertEquals("Failed to save user. Please try again.", result.errorMessage)
    }
}