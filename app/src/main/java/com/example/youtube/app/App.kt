package com.example.youtube.app

import android.app.Application
import com.example.youtube.di.generalModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(generalModule)
        }
    }
}