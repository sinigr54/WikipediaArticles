package com.sinigr.wikipediaarticles.network.ioc

import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.network.RestServiceFactory
import com.sinigr.wikipediaarticles.network.network_manager.CoroutineNetworkManager
import com.sinigr.wikipediaarticles.network.parser.IResponseParser
import com.sinigr.wikipediaarticles.network.parser.WikiResponseQueryParser
import com.sinigr.wikipediaarticles.network.responses.QueryResponse
import com.sinigr.wikipediaarticles.network.services.INearbyPlacesService
import org.koin.dsl.module

val networkModule = module {

    single { RestServiceFactory() }

    single { CoroutineNetworkManager() }

    single<IResponseParser<QueryResponse, List<ArticleEntity>>> { WikiResponseQueryParser() }

    factory { get<RestServiceFactory>().createService(INearbyPlacesService::class.java) }

}