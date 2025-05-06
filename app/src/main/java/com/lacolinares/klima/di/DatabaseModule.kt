package com.lacolinares.klima.di

import android.app.Application
import androidx.room.Room
import com.lacolinares.klima.data.local.database.KlimaDatabase
import com.lacolinares.klima.data.local.database.dao.UserDao
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = get<Application>(),
            klass = KlimaDatabase::class.java,
            name = "klima_db"
        ).build()
    }

    single<UserDao> { get<KlimaDatabase>().userDao() }
}