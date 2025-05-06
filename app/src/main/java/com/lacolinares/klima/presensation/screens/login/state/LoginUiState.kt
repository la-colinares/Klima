package com.lacolinares.klima.presensation.screens.login.state

import com.lacolinares.klima.domain.validation.FieldType

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val fieldErrors: Map<FieldType, String> = emptyMap(),
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)