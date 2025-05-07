package com.lacolinares.klima.domain.usecase

import com.lacolinares.klima.domain.repository.UserRepository

class GetUserFullNameUseCase(private val userRepository: UserRepository) {

    suspend fun execute(email: String): String {
        return userRepository.findByEmail(email)?.fullName.orEmpty()
    }

}