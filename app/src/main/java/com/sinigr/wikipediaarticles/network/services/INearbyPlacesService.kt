package com.sinigr.wikipediaarticles.network.services

import com.sinigr.wikipediaarticles.network.responses.QueryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface INearbyPlacesService {
    @GET("/w/api.php")
    fun getNearbyPlacesAsync(@QueryMap params: Map<String, String>): Deferred<Response<QueryResponse>>
}