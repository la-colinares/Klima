package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveUserSessionUseCaseTest {

    private val sessionManager: SessionManager = mockk()
    private val saveUserSessionUseCase = SaveUserSessionUseCase(sessionManager)

    @Test
    fun `save session when email is provided`() = runTest {
        // Given
        val email = "test@example.com"
        coEvery { sessionManager.saveSession(email) } just Runs

        // When
        saveUserSessionUseCase.execute(email)

        // Then
        coVerify { sessionManager.saveSession(email) }
    }
}