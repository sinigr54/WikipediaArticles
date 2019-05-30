package com.sinigr.wikipediaarticles.modules.main.ioc

import com.sinigr.wikipediaarticles.location.ILocationService
import com.sinigr.wikipediaarticles.location.LocationService
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.ArticlesListInteractor
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.IArticlesListInteractor
import com.sinigr.wikipediaarticles.modules.main.articles_list.presenter.ArticlesListPresenter
import com.sinigr.wikipediaarticles.modules.main.articles_list.presenter.IArticlesListPresenter
import com.sinigr.wikipediaarticles.network.RestServiceFactory
import com.sinigr.wikipediaarticles.network.network_manager.CoroutineNetworkManager
import com.sinigr.wikipediaarticles.network.services.INearbyPlacesService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {

    single<ILocationService> { LocationService(androidContext()) }

    factory<IArticlesListInteractor> { ArticlesListInteractor(get(), get(), get()) }

    factory<IArticlesListPresenter> { ArticlesListPresenter(get(), get()) }

}