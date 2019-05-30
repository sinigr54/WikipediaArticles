package com.sinigr.wikipediaarticles.modules.main.articles_list.presenter

import com.sinigr.wikipediaarticles.base.IPresenter
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.IArticlesListView

interface IArticlesListPresenter : IPresenter<IArticlesListView> {
    fun loadArticles()
}