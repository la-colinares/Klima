package com.lacolinares.klima.domain.validation

data class SignUpResult(
    val success: Boolean,
    val fieldErrors: Map<FieldType, String> = emptyMap(),
    val errorMessage: String? = null
)