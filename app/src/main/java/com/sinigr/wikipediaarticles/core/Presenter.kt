package com.sinigr.wikipediaarticles.core

interface Presenter<View : com.sinigr.wikipediaarticles.core.View> {

  var view: View?

  fun attachView(view: View) {
    this.view = view
  }

  fun detachView() {
    view = null
  }
}