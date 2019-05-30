package com.sinigr.wikipediaarticles.location

interface ILocationService {
    fun getLocation(onSuccess: (latitude: Double, longitude: Double) -> Unit)
}