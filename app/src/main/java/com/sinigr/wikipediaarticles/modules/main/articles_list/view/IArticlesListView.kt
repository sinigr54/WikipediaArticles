package com.sinigr.wikipediaarticles.modules.main.articles_list.view

import com.sinigr.wikipediaarticles.base.IView
import com.sinigr.wikipediaarticles.entity.ArticleEntity

interface IArticlesListView : IView {
    fun onArticlesLoaded(articles: List<ArticleEntity>)
    fun onError(message: String)
}