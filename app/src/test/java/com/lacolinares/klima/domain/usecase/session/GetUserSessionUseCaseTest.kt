package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager
import com.lacolinares.klima.domain.model.Session
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserSessionUseCaseTest {

    private val sessionManager: SessionManager = mockk()
    private val getUserSessionUseCase = GetUserSessionUseCase(sessionManager)

    @Test
    fun `retrieve session when session exists`() = runTest {
        // Given
        val session = Session("test@example.com")
        coEvery { sessionManager.sessionFlow } returns flowOf(session)

        // When
        val result = getUserSessionUseCase.execute()

        // Then
        val emittedSession = result.firstOrNull()
        assertEquals(session, emittedSession)
    }

    @Test
    fun `return null when no session exists`() = runTest {
        // Given
        coEvery { sessionManager.sessionFlow } returns flowOf(null)

        // When
        val result = getUserSessionUseCase.execute()

        // Then
        val emittedSession = result.firstOrNull()
        assertEquals(null, emittedSession)
    }
}