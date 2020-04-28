package com.sinigr.wikipediaarticles.network.responses

import com.sinigr.wikipediaarticles.entity.ArticleEntity

data class ArticlesResponse(
  val articles: List<ArticleEntity> = emptyList()
) {

  companion object {
    val EMPTY = ArticlesResponse()
  }
}