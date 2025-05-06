package com.lacolinares.klima.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lacolinares.klima.data.local.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT COUNT(*) > 0 FROM tbl_users WHERE email = :email")
    suspend fun isEmailTaken(email: String): Boolean

    @Query("SELECT * FROM tbl_users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

}