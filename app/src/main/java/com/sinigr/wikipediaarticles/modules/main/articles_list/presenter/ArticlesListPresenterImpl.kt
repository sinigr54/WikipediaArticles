package com.sinigr.wikipediaarticles.modules.main.articles_list.presenter

import com.sinigr.wikipediaarticles.location.LocationService
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.ArticlesListInteractor
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.ArticlesListView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.SerialDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticlesListPresenterImpl(
  private val interactor: ArticlesListInteractor,
  private val locationService: LocationService
) : ArticlesListPresenter {

  private val disposable = SerialDisposable()

  override var view: ArticlesListView? = null

  override fun loadArticles() {
    locationService.getLocation { latitude, longitude ->
      disposable.set(
        interactor.loadArticles(latitude, longitude)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({
            view?.onArticlesLoaded(it)
          }, {
            view?.onError(it.message ?: "")
          })
      )
    }
  }

  override fun detachView() {
    if (!disposable.isDisposed) {
      disposable.dispose()
    }

    super.detachView()
  }
}