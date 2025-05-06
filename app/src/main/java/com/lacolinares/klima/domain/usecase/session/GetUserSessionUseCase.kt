package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager
import com.lacolinares.klima.domain.model.Session
import kotlinx.coroutines.flow.Flow

class GetUserSessionUseCase(private val sessionManager: SessionManager) {
    fun execute(): Flow<Session?> = sessionManager.sessionFlow
}