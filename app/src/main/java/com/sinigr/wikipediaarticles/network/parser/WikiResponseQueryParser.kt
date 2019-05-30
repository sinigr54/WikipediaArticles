package com.sinigr.wikipediaarticles.network.parser

import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.network.responses.QueryResponse

class WikiResponseQueryParser : IResponseParser<QueryResponse, List<ArticleEntity>> {

    companion object {
        private const val PAGES_KEY = "pages"
        private const val TITLE_KEY = "title"
        private const val IMAGES_KEY = "images"
    }

    override fun parse(data: QueryResponse): List<ArticleEntity> {
        val result = arrayListOf<ArticleEntity>()

        if (data.query?.has(PAGES_KEY) == true) {
            val pagesObject = data.query.getAsJsonObject(PAGES_KEY)
            val keySet = pagesObject.keySet()

            keySet.forEach { key ->
                val page = pagesObject[key].asJsonObject

                val title = if (page.has(TITLE_KEY)) page[TITLE_KEY].asString else ""
                val imagesCount = if (page.has(IMAGES_KEY)) page[IMAGES_KEY].asJsonArray.count() else 0

                result.add(ArticleEntity(title, imagesCount))
            }
        }

        return result
    }
}