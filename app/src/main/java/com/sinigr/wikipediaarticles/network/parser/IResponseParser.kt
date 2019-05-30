package com.sinigr.wikipediaarticles.network.parser

interface IResponseParser<in From, out To> {
    fun parse(data: From): To
}