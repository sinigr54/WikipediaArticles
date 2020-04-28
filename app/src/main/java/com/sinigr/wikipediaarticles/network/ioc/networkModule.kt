package com.sinigr.wikipediaarticles.network.ioc

import android.content.Context
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sinigr.wikipediaarticles.BuildConfig
import com.sinigr.wikipediaarticles.modules.main.articles_list.interactor.ArticlesResponseDeserializer
import com.sinigr.wikipediaarticles.network.Constants
import com.sinigr.wikipediaarticles.network.responses.ArticlesResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

  factory {
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  factory {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
      builder.addInterceptor(get<HttpLoggingInterceptor>())
    }

    builder.build()
  }

  factory {
    RxJava3CallAdapterFactory.create()
  }

  factory {
    GsonBuilder()
      .registerTypeAdapter(ArticlesResponse::class.java, ArticlesResponseDeserializer())
      .create()
  }

  factory {
    GsonConverterFactory.create(get<Gson>())
  }

  single {
    Retrofit.Builder()
      .client(get<OkHttpClient>())
      .baseUrl(getProperty(Constants.BASE_URL_KEY, ""))
      .addConverterFactory(get<GsonConverterFactory>())
      .addCallAdapterFactory(get<RxJava3CallAdapterFactory>())
      .build()
  }

  single {
    Glide.with(get<Context>())
  }

}