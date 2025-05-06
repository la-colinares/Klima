package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager
import com.lacolinares.klima.domain.model.Session
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Test

class ClearUserSessionUseCaseTest {

    private val sessionManager: SessionManager = mockk()
    private val clearUserSessionUseCase = ClearUserSessionUseCase(sessionManager)

    @Test
    fun `clear session when invoked`() = runBlocking {
        // Given
        val sessionFlow = MutableStateFlow<Session?>(Session("test@example.com"))
        every { sessionManager.sessionFlow } returns sessionFlow
        coEvery { sessionManager.clearSession() } coAnswers { sessionFlow.update { null } }

        // When
        clearUserSessionUseCase.execute()

        // Then
        assertNull(sessionFlow.value)
        coVerify { sessionManager.clearSession() }
    }
}