package com.github.kornilovmikhail.spoticloud.domain.interfaces

import com.github.kornilovmikhail.spoticloud.data.network.model.TokenResponse

interface TokenHelper {

    fun getToken(): String?

    fun saveToken(tokenResponse: TokenResponse)

    fun refresh()
}
