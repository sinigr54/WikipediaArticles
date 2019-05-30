package com.sinigr.wikipediaarticles.errors

import com.sinigr.wikipediaarticles.R
import com.sinigr.wikipediaarticles.application.WikiArticlesApplication

const val DEFAULT_ERROR_CODE = -1

enum class ErrorConstants(val code: Int, val message: String) {
    PARSING_ERROR(DEFAULT_ERROR_CODE, WikiArticlesApplication.instance.getString(R.string.json_parsing_error))
}