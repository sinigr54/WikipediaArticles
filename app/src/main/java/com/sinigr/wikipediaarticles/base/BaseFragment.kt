package com.sinigr.wikipediaarticles.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IView {
    override fun showLoadingDialog() {
        // Nothing
    }

    override fun dismissLoadingDialog() {
        // Nothing
    }
}