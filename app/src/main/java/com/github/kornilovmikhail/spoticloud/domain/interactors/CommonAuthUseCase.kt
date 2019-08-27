package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.CommonUserRepository
import javax.inject.Inject

class CommonAuthUseCase @Inject constructor(
    private val commonUserRepository: CommonUserRepository
) {

    fun checkAuth(): Boolean = commonUserRepository.checkAuth()

    fun saveAuthStatus(status: Boolean) {
        commonUserRepository.saveAuthStatus(status)
    }
}
