package com.dragonslotos.psb_test.App

import android.app.Application
import com.dragonslotos.psb_test.di.app_module
import com.dragonslotos.psb_test.di.data_module
import com.dragonslotos.psb_test.di.domain_module
import com.dragonslotos.psb_test.di.retrofit_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        //lload koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(app_module, data_module, domain_module, retrofit_module))
        }
    }
}