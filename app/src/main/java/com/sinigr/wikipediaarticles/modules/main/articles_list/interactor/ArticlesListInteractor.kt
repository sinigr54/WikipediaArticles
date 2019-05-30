package com.sinigr.wikipediaarticles.modules.main.articles_list.interactor

import com.sinigr.wikipediaarticles.base.interactor.subscriber.Subscriber
import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.errors.ErrorConstants
import com.sinigr.wikipediaarticles.network.network_manager.CoroutineNetworkManager
import com.sinigr.wikipediaarticles.network.network_manager.Result
import com.sinigr.wikipediaarticles.network.parser.IResponseParser
import com.sinigr.wikipediaarticles.network.parser.WikiResponseQueryParser
import com.sinigr.wikipediaarticles.network.responses.QueryResponse
import com.sinigr.wikipediaarticles.network.services.INearbyPlacesService
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.lang.IllegalStateException

class ArticlesListInteractor(
    private val articlesNetworkService: INearbyPlacesService,
    private val networkManager: CoroutineNetworkManager,
    private val queryParser: IResponseParser<QueryResponse, List<ArticleEntity>>
) : IArticlesListInteractor {

    private val supervisorJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + supervisorJob)

    override fun loadArticles(latitude: Double, longitude: Double, subscriber: Subscriber<List<ArticleEntity>>) {
        scope.launch {
            try {
                val result = networkManager.safeCall {
                    val params = fillParameters(latitude, longitude)
                    articlesNetworkService.getNearbyPlacesAsync(params).await()
                }

                var parsedResult = listOf<ArticleEntity>()
                when (result) {
                    is Result.Success -> {
                        parsedResult = queryParser.parse(result.data)
                    }
                }

                withContext(Dispatchers.Main) {
                    when (result) {
                        is Result.Success -> {
                            subscriber.onSuccess(parsedResult)
                        }
                        is Result.Error -> {
                            subscriber.onError(result.code, result.message)
                        }
                    }

                    subscriber.onFinish()
                }
            } catch (e: IllegalStateException) {
                withContext(Dispatchers.Main) {
                    subscriber.onError(ErrorConstants.PARSING_ERROR.code, ErrorConstants.PARSING_ERROR.message)
                    subscriber.onFinish()
                }
            } catch (e: HttpException) {
                // TODO Processing error
            } catch (e: Exception) {
                // TODO Processing error
            }
        }
    }

    private fun fillParameters(latitude: Double, longitude: Double): Map<String, String> {
        return hashMapOf(
            "action" to "query",
            "prop" to "images",
            "inprop" to "url",
            "pithumbsize" to "144",
            "generator" to "geosearch",
            "ggsradius" to "10000",
            "ggslimit" to "50",
            "ggscoord" to "$latitude|$longitude",
            "format" to "json"
        )
    }

    override fun stopWork() {
        scope.coroutineContext.cancelChildren()
    }
}