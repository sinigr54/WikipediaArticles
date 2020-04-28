package com.sinigr.wikipediaarticles.location

interface LocationService {
  fun getLocation(onSuccess: (latitude: Double, longitude: Double) -> Unit)
}