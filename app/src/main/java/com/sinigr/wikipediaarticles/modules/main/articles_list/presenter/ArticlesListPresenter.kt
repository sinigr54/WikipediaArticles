package com.sinigr.wikipediaarticles.modules.main.articles_list.presenter

import com.sinigr.wikipediaarticles.core.Presenter
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.ArticlesListView

interface ArticlesListPresenter : Presenter<ArticlesListView> {
  fun loadArticles()
}