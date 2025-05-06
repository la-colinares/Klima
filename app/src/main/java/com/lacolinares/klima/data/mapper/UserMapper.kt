package com.lacolinares.klima.data.mapper

import com.lacolinares.klima.data.local.database.entities.UserEntity
import com.lacolinares.klima.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        fullName = fullName,
        email = email,
        createdAt = createdAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        fullName = fullName,
        email = email,
        password = password,
        createdAt = createdAt
    )
}