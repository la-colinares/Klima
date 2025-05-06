package com.lacolinares.klima.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lacolinares.klima.data.local.database.dao.UserDao
import com.lacolinares.klima.data.local.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KlimaDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}