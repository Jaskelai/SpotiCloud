package com.github.kornilovmikhail.spoticloud.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleEventLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        super.observe(owner, Observer { data ->
            if (data == null) return@Observer

            observer.onChanged(data)
            value = null
        })
    }

    @MainThread
    fun sendEvent(data: T) {
        value = data
    }
}