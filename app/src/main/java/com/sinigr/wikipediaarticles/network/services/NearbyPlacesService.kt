package com.sinigr.wikipediaarticles.network.services

import com.sinigr.wikipediaarticles.network.responses.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NearbyPlacesService {
  @GET("/w/api.php")
  fun getNearbyPlaces(@QueryMap params: Map<String, String>): Single<ArticlesResponse>
}