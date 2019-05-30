package com.sinigr.wikipediaarticles.base.interactor.subscriber

abstract class Subscriber<T : Any> : ISubscriber<T> {

    override fun onSuccess(data: T) {

    }

    override fun onError(code: Int, message: String) {

    }

    override fun onFinish() {

    }
}