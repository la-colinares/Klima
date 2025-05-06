package com.lacolinares.klima.domain.model

data class User(
    val id: Int = 0,
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val createdAt: Long = System.currentTimeMillis()
)