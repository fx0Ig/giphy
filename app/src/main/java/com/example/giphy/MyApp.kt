package com.example.giphy

import android.app.Application
import com.example.giphy.di.appModule
import org.koin.android.ext.android.startKoin


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(appModule)
        )
    }


}