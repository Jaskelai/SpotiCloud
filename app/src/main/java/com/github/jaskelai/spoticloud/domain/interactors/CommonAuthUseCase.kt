package com.github.jaskelai.spoticloud.domain.interactors

import com.github.jaskelai.spoticloud.domain.interfaces.CommonUserRepository
import javax.inject.Inject

class CommonAuthUseCase @Inject constructor(
    private val commonUserRepository: CommonUserRepository
) {

    fun checkAuth(): Boolean = commonUserRepository.checkAuth()

    fun saveAuthStatus(status: Boolean) {
        commonUserRepository.saveAuthStatus(status)
    }
}
