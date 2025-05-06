package com.lacolinares.klima.data.repository

import com.lacolinares.klima.data.local.database.dao.UserDao
import com.lacolinares.klima.data.local.database.entities.UserEntity
import com.lacolinares.klima.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {

    override suspend fun insertUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return userDao.isEmailTaken(email)
    }

    override suspend fun findByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
}