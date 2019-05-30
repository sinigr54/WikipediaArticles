package com.sinigr.wikipediaarticles.modules.main.articles_list.interactor

import com.sinigr.wikipediaarticles.base.interactor.ICoroutineInteractor
import com.sinigr.wikipediaarticles.base.interactor.subscriber.Subscriber
import com.sinigr.wikipediaarticles.entity.ArticleEntity

interface IArticlesListInteractor : ICoroutineInteractor {
    fun loadArticles(latitude: Double, longitude: Double, subscriber: Subscriber<List<ArticleEntity>>)
}