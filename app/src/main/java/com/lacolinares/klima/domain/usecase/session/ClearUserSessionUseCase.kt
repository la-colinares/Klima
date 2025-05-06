package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager

class ClearUserSessionUseCase(private val sessionManager: SessionManager) {

    suspend fun execute() {
        sessionManager.clearSession()
    }

}