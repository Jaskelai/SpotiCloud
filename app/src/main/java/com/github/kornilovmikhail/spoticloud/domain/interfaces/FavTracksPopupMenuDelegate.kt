package com.github.kornilovmikhail.spoticloud.domain.interfaces

import android.view.View
import com.github.kornilovmikhail.spoticloud.domain.model.Track

interface FavTracksPopupMenuDelegate {

    fun showFavTracksPopup(
        containerView: View,
        track: Track,
        deleteListener: () -> Unit
    )
}
