package com.lacolinares.klima.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("full_name")
    val fullName: String,
    val email: String,
    val password: String,
    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis()
)