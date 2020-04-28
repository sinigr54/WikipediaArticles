package com.sinigr.wikipediaarticles.core.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class PreferencesManager(
  val gson: Gson,
  private val sharedPreferences: SharedPreferences
) {

  fun edit(): SharedPreferences.Editor {
    return sharedPreferences.edit()
  }

  fun clean(): Completable {
    return Completable.fromAction {
      sharedPreferences.edit().clear().apply()
    }
  }

  fun putString(key: String, value: String): Completable {
    return Completable.fromAction {
      edit().putString(key, value).apply()
    }
  }

  fun getString(key: String): Observable<String> {
    return Observable.create { emitter ->
      val string = sharedPreferences.getString(key, null)
      if (string == null) {
        emitter.onComplete()
      } else {
        emitter.onNext(string)
        emitter.onComplete()
      }
    }
  }

  inline fun <reified T> save(key: String, model: T): Completable {
    return Completable.fromAction {
      edit().putString(key, gson.toJson(model, object : TypeToken<T>() {}.type)).apply()
    }
  }

  fun <T> load(key: String, clazz: Class<T>): Observable<T> {
    return getString(key)
      .map { json ->
        gson.fromJson(json, clazz)
      }
  }

  fun remove(vararg keys: String): Completable {
    return Completable.fromAction {
      val editor = edit()
      for (key in keys) {
        editor.remove(key)
      }

      editor.apply()
    }
  }
}