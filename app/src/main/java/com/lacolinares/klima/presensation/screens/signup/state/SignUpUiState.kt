package com.lacolinares.klima.presensation.screens.signup.state

import com.lacolinares.klima.domain.validation.FieldType

data class SignUpUiState(
    val form: SignUpFormState = SignUpFormState(),
    val fieldErrors: Map<FieldType, String> = emptyMap(),
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)