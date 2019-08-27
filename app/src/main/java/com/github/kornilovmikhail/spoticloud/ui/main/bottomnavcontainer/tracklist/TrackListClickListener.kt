package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist

import com.github.kornilovmikhail.spoticloud.domain.model.Track

interface TrackListClickListener {

    fun onClick(track: Track?)
}
