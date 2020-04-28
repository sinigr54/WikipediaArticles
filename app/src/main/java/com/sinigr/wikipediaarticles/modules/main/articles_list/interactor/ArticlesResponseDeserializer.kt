package com.sinigr.wikipediaarticles.modules.main.articles_list.interactor

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.network.responses.ArticlesResponse
import java.lang.reflect.Type

class ArticlesResponseDeserializer : JsonDeserializer<ArticlesResponse> {

  override fun deserialize(
    json: JsonElement,
    typeOfT: Type?,
    context: JsonDeserializationContext?
  ): ArticlesResponse {
    val jsonObject = json.asJsonObject
    val query = if (jsonObject.has(QUERY_KEY)) {
      jsonObject[QUERY_KEY].asJsonObject
    } else {
      return ArticlesResponse.EMPTY
    }

    val articles = arrayListOf<ArticleEntity>()

    if (query.has(PAGES_KEY)) {
      val pagesObject = query.getAsJsonObject(PAGES_KEY)
      val keySet = pagesObject.keySet()

      keySet.forEach { key ->
        val page = pagesObject[key].asJsonObject

        val title = if (page.has(TITLE_KEY)) page[TITLE_KEY].asString else ""
        val imagesCount = if (page.has(IMAGES_KEY)) page[IMAGES_KEY].asJsonArray.count() else 0

        articles.add(ArticleEntity(title, imagesCount))
      }
    }

    return ArticlesResponse(articles)
  }

  private companion object {
    const val QUERY_KEY = "query"
    const val PAGES_KEY = "pages"
    const val TITLE_KEY = "title"
    const val IMAGES_KEY = "images"
  }
}