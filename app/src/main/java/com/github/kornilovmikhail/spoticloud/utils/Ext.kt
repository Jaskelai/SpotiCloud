package com.github.kornilovmikhail.spoticloud.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectParentViewModel(factory: ViewModelProvider.Factory): T {
    parentFragment?.let {
        return ViewModelProviders.of(it, factory)[T::class.java]
    }
    return ViewModelProviders.of(this, factory)[T::class.java]
}
