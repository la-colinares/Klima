package com.lacolinares.klima.domain.validation

object EmailValidator {

    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    fun isValid(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }

}