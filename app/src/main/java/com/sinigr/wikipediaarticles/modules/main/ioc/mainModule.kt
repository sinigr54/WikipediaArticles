package com.sinigr.wikipediaarticles.modules.main.ioc

import android.app.Activity
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.ArticlesListInteractorImpl
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.ArticlesListInteractor
import com.sinigr.wikipediaarticles.modules.main.articles_list.presenter.ArticlesListPresenter
import com.sinigr.wikipediaarticles.modules.main.articles_list.presenter.ArticlesListPresenterImpl
import com.sinigr.wikipediaarticles.modules.main.articles_list.store.ArticlesStore
import com.sinigr.wikipediaarticles.modules.main.articles_list.store.ArticlesStoreImpl
import com.sinigr.wikipediaarticles.network.services.NearbyPlacesService
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {

  factory { get<Retrofit>().create(NearbyPlacesService::class.java) }
  factory<ArticlesStore> { ArticlesStoreImpl(get()) }

  factory<ArticlesListInteractor> { ArticlesListInteractorImpl(get(), get()) }

  factory<ArticlesListPresenter> { (activity: Activity) ->
    ArticlesListPresenterImpl(
      get(),
      get { parametersOf(activity) }
    )
  }

}