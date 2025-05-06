package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.domain.repository.UserRepository
import com.lacolinares.klima.domain.validation.EmailValidator
import com.lacolinares.klima.domain.validation.FieldType
import com.lacolinares.klima.domain.validation.LoginResult
import com.lacolinares.klima.util.SecurityUtils

class LoginUseCase(private val userRepository: UserRepository) {

    private val invalidCredentials = LoginResult(success = false, errorMessage = "Invalid username or password")

    suspend fun execute(email: String, password: String): LoginResult {
        val fieldErrors = mutableMapOf<FieldType, String>()

        when {
            email.isBlank() -> fieldErrors[FieldType.EMAIL] = "Email is required"
            !EmailValidator.isValid(email) -> fieldErrors[FieldType.EMAIL] = "Invalid Email Address"
        }

        if (password.isBlank()) fieldErrors[FieldType.PASSWORD] = "Password is required"

        if (fieldErrors.isNotEmpty()) {
            return LoginResult(success = false, fieldErrors = fieldErrors)
        }

        return runCatching {
            val user = userRepository.findByEmail(email)
            val hashedInputPassword = SecurityUtils.hashPassword(password)
            if (user?.password.orEmpty() == hashedInputPassword) {
                LoginResult(success = true)
            } else {
                invalidCredentials
            }
        }.getOrElse {
            LoginResult(success = false, errorMessage = "Error occurred. Please try again.")
        }

    }
}