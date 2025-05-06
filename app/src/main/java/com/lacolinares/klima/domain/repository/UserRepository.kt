package com.lacolinares.klima.domain.repository

import com.lacolinares.klima.data.local.database.entities.UserEntity

interface UserRepository {
    suspend fun insertUser(userEntity: UserEntity)
    suspend fun isEmailTaken(email: String): Boolean
    suspend fun findByEmail(email: String): UserEntity?
}