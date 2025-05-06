package com.lacolinares.klima

import android.app.Application
import com.lacolinares.klima.di.databaseModule
import com.lacolinares.klima.di.repositoryModule
import com.lacolinares.klima.di.useCaseModule
import com.lacolinares.klima.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KlimaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KlimaApp)
            modules(
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}