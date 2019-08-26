package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist

import com.github.jaskelai.spoticloud.domain.model.Track

interface TrackListClickListener {

    fun onClick(track: Track?)
}
