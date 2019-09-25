package com.github.kornilovmikhail.spoticloud.domain.interactors

import com.github.kornilovmikhail.spoticloud.domain.interfaces.repository.CommonUserRepository
import javax.inject.Inject

class CommonAuthUseCase @Inject constructor(
    private val commonUserRepository: CommonUserRepository
) {

    fun isAuthed(): Boolean = commonUserRepository.isAuthed()

    fun saveAuthStatus(status: Boolean) {
        commonUserRepository.saveAuthStatus(status)
    }
}
