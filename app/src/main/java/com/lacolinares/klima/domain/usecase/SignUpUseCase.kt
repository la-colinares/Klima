package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.data.local.database.entities.UserEntity
import com.lacolinares.klima.domain.repository.UserRepository
import com.lacolinares.klima.domain.validation.EmailValidator
import com.lacolinares.klima.domain.validation.FieldType
import com.lacolinares.klima.domain.validation.SignUpResult
import com.lacolinares.klima.util.SecurityUtils

class SignUpUseCase(
    private val userRepository: UserRepository
){
    suspend fun execute(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult {

        val fieldErrors = mutableMapOf<FieldType, String>()

        when {
            fullName.trim().isBlank() -> fieldErrors[FieldType.FULL_NAME] = "Full name is required"
            fullName.length < 6 -> fieldErrors[FieldType.FULL_NAME] = "Full name must be at least 6 characters"
        }

        when {
            email.trim().isBlank() -> fieldErrors[FieldType.EMAIL] = "Email is required"
            !EmailValidator.isValid(email) -> fieldErrors[FieldType.EMAIL] = "Invalid Email Address"
        }

        when {
            password.trim().isBlank() -> fieldErrors[FieldType.PASSWORD] = "Password is required"
            password.length < 6 -> fieldErrors[FieldType.PASSWORD] = "Password must be at least 6 characters"
            password != confirmPassword -> fieldErrors[FieldType.CONFIRM_PASSWORD] = "Password do not match"
        }

        if (confirmPassword.trim().isBlank()){
            fieldErrors[FieldType.CONFIRM_PASSWORD] = "Confirm Password is required"
        }

        if (fieldErrors.isNotEmpty()) {
            return SignUpResult(success = false, fieldErrors = fieldErrors)
        }

        val usernameTaken = userRepository.isEmailTaken(email)
        if (usernameTaken) {
            return SignUpResult(
                success = false,
                fieldErrors = mapOf(FieldType.EMAIL to "Email already exists")
            )
        }

        return runCatching {
            val hashedPassword = SecurityUtils.hashPassword(password)
            val userEntity = UserEntity(
                fullName = fullName,
                email = email,
                password = hashedPassword,
                createdAt = System.currentTimeMillis()
            )

            userRepository.insertUser(userEntity)
            SignUpResult(success = true)
        }.getOrElse {
            SignUpResult(success = false, errorMessage = "Failed to save user. Please try again.")
        }
    }
}