package com.lacolinares.klima.domain.usecase.session

import com.lacolinares.klima.data.local.datastore.SessionManager

class SaveUserSessionUseCase(private val sessionManager: SessionManager) {

    suspend fun execute(email: String) {
        sessionManager.saveSession(email)
    }

}