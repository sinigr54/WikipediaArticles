package com.sinigr.wikipediaarticles.application

import android.app.Application
import android.util.Log
import com.sinigr.wikipediaarticles.modules.main.ioc.mainModule
import com.sinigr.wikipediaarticles.network.ioc.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WikiArticlesApplication : Application() {

    companion object {
        private const val TAG = "Application"

        lateinit var instance: WikiArticlesApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@WikiArticlesApplication)
            modules(listOf(networkModule, mainModule))
        }
    }

}