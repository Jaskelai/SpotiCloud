package com.github.kornilovmikhail.spoticloud.domain.interfaces

interface UserSpotifyRepository{

    fun auth(any: Any?)

    fun isAuthed(): Boolean
}
