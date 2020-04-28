package com.sinigr.wikipediaarticles.modules.main.articles_list.store

import com.sinigr.wikipediaarticles.core.preferences.PreferencesManager
import com.sinigr.wikipediaarticles.network.responses.ArticlesResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface ArticlesStore {
  fun saveArticles(articles: ArticlesResponse): Completable
  fun loadArticles(): Observable<ArticlesResponse>
}

class ArticlesStoreImpl(
  private val preferencesManager: PreferencesManager
) : ArticlesStore {

  override fun saveArticles(articles: ArticlesResponse): Completable {
    return preferencesManager.save(ARTICLES_KEY, articles)
  }

  override fun loadArticles(): Observable<ArticlesResponse> {
    return preferencesManager.load(ARTICLES_KEY, ArticlesResponse::class.java)
  }

  private companion object {
    const val ARTICLES_KEY = "articles"
  }
}