package com.github.kornilovmikhail.spoticloud.utils

import org.mockito.Mockito

fun <T> anyNotNull(): T {
    Mockito.any<T>()
    return uninitialized()
}
fun <T> uninitialized(): T = null as T
