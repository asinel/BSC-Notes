package com.sinelnikov.bsc

import android.app.Application
import com.sinelnikov.bsc.di.appModule
import com.sinelnikov.bsc.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, appModule))
        }
    }
}