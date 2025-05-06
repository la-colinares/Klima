package com.lacolinares.klima.domain.validation

data class LoginResult(
    val success: Boolean,
    val fieldErrors: Map<FieldType, String> = emptyMap(),
    val errorMessage: String? = null
)