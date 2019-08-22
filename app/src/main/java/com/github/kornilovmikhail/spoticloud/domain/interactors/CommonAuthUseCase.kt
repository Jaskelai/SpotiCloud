package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.CommonUserRepository
import io.reactivex.Maybe
import javax.inject.Inject

class CommonAuthUseCase @Inject constructor(
    private val commonUserRepository: CommonUserRepository
) {

    fun checkAuth(): Maybe<Boolean> = commonUserRepository.checkAuth()

    fun saveAuthStatus(status: Boolean) {
        commonUserRepository.saveAuthStatus(status)
    }
}
