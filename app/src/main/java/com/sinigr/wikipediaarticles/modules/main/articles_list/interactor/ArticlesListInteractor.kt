package com.sinigr.wikipediaarticles.modules.main.articles_list.interactor

import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.modules.main.articles_list.store.ArticlesStore
import com.sinigr.wikipediaarticles.network.responses.ArticlesResponse
import com.sinigr.wikipediaarticles.network.services.NearbyPlacesService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ArticlesListInteractor {
  fun loadArticles(latitude: Double, longitude: Double): Observable<List<ArticleEntity>>
  fun refreshArticles(latitude: Double, longitude: Double): Single<ArticlesResponse>
}

class ArticlesListInteractorImpl(
  private val articlesNetworkService: NearbyPlacesService,
  private val articlesStore: ArticlesStore
) : ArticlesListInteractor {

  override fun loadArticles(latitude: Double, longitude: Double): Observable<List<ArticleEntity>> {
    return articlesStore.loadArticles()
      .concatWith(refreshArticles(latitude, longitude).toObservable())
      .take(1)
      .map {
        it.articles
      }
  }

  override fun refreshArticles(latitude: Double, longitude: Double): Single<ArticlesResponse> {
    return articlesNetworkService.getNearbyPlaces(fillParameters(latitude, longitude))
      .flatMap {
        articlesStore.saveArticles(it).toSingleDefault(it)
      }
  }

  private fun fillParameters(latitude: Double, longitude: Double): Map<String, String> {
    return hashMapOf(
      "action" to "query",
      "prop" to "images",
      "inprop" to "url",
      "pithumbsize" to "144",
      "generator" to "geosearch",
      "ggsradius" to "10000",
      "ggslimit" to "50",
      "ggscoord" to "$latitude|$longitude",
      "format" to "json"
    )
  }
}