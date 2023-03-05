package com.example.kotlintechinterview.app

import android.app.Application
import com.example.kotlintechinterview.di.appModule
import com.example.kotlintechinterview.di.dataModule
import com.example.kotlintechinterview.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            loadKoinModules(listOf(appModule, domainModule, dataModule))
        }
    }
}