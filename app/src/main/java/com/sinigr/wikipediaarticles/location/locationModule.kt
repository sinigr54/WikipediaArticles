package com.sinigr.wikipediaarticles.location

import android.app.Activity
import com.google.android.gms.location.LocationServices
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val locationModule = module {

  factory { (activity: Activity) -> LocationServices.getFusedLocationProviderClient(activity) }

  single<LocationService> { (activity: Activity) ->
    LocationServiceImpl(get { parametersOf(activity) })
  }

}