package com.sinigr.wikipediaarticles.application

import android.content.Context
import com.google.gson.Gson
import com.sinigr.wikipediaarticles.R
import com.sinigr.wikipediaarticles.core.preferences.PreferencesManager
import org.koin.dsl.module

val applicationModule = module {
  factory {
    val context = get<Context>()
    val sharedPreferences = context.getSharedPreferences(
      context.getString(R.string.shared_preferences_file_key),
      Context.MODE_PRIVATE
    )

    PreferencesManager(Gson(), sharedPreferences)
  }
}