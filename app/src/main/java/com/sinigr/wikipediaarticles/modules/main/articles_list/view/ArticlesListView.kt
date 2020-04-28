package com.sinigr.wikipediaarticles.modules.main.articles_list.view

import com.sinigr.wikipediaarticles.core.View
import com.sinigr.wikipediaarticles.entity.ArticleEntity

interface ArticlesListView : View {
  fun onArticlesLoaded(articles: List<ArticleEntity>)
  fun onError(message: String)
}