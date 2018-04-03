package com.example.mysussrx.model

import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by hzg on 2018/2/7.
 */
abstract class ResultSubscriber<T> : DisposableSubscriber<T>() {
    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {
        cancel()
    }

    override fun onError(t: Throwable?) {
        onFailure(t!!)
    }

    abstract fun onSuccess(t: T)
    abstract fun onFailure(e: Throwable)
}