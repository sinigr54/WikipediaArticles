package com.sinigr.wikipediaarticles.modules.main.articles_list.presenter

import com.sinigr.wikipediaarticles.base.interactor.subscriber.Subscriber
import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.location.ILocationService
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.IArticlesListInteractor
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.IArticlesListView

class ArticlesListPresenter(
    private val interactor: IArticlesListInteractor,
    private val locationService: ILocationService
) : IArticlesListPresenter {

    override var view: IArticlesListView? = null

    override fun loadArticles() {
        locationService.getLocation { latitude, longitude ->
            interactor.loadArticles(latitude, longitude, object : Subscriber<List<ArticleEntity>>() {
                override fun onSuccess(data: List<ArticleEntity>) {
                    view?.onArticlesLoaded(data)
                }

                override fun onError(code: Int, message: String) {
                    view?.onError(message)
                }
            })
        }
    }

    override fun detachView() {
        interactor.stopWork()

        super.detachView()
    }
}