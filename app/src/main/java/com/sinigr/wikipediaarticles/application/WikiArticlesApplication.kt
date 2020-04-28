package com.sinigr.wikipediaarticles.application

import android.app.Application
import com.sinigr.wikipediaarticles.location.locationModule
import com.sinigr.wikipediaarticles.modules.main.ioc.mainModule
import com.sinigr.wikipediaarticles.network.ioc.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class WikiArticlesApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@WikiArticlesApplication)
      androidFileProperties()
      modules(
        listOf(
          applicationModule,
          networkModule,
          locationModule,
          mainModule
        )
      )
    }
  }

}