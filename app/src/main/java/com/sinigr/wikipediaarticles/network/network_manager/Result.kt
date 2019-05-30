package com.sinigr.wikipediaarticles.network.network_manager

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val code: Int, val message: String) : Result<Nothing>()
}