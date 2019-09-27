package com.github.kornilovmikhail.spoticloud.domain.interfaces

import android.view.View
import com.github.kornilovmikhail.spoticloud.domain.model.Track

interface SearchTrendsPopupMenuDelegate {

    fun showSearchTrendsPopup(containerView: View, track: Track)
}
