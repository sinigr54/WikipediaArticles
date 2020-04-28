package com.sinigr.wikipediaarticles.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*

class LocationServiceImpl(private val providerClient: FusedLocationProviderClient) :
  LocationService {

  @SuppressLint("MissingPermission")
  override fun getLocation(onSuccess: (latitude: Double, longitude: Double) -> Unit) {
    val locationRequest = LocationRequest()
    providerClient.requestLocationUpdates(
      locationRequest,
      object : LocationCallback() {
        override fun onLocationResult(location: LocationResult?) {
          providerClient.removeLocationUpdates(this)

          onSuccess(
            location?.lastLocation?.latitude ?: 0.0,
            location?.lastLocation?.longitude ?: 0.0
          )
        }
      },
      null
    )
  }
}