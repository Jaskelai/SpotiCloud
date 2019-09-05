package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import com.github.kornilovmikhail.spoticloud.domain.model.Track

interface TrackClickListener {

    fun onTrackClicked(track: Track?)
}
