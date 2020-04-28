package com.sinigr.wikipediaarticles.core

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View {
  override fun showLoadingDialog() {
    // Nothing
  }

  override fun dismissLoadingDialog() {
    // Nothing
  }
}