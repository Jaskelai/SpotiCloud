package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.popupmenu

import android.content.Context
import android.view.View
import com.github.kornilovmikhail.spoticloud.domain.model.Track

interface PopupMenuDelegate {

    fun showPopup(context: Context, containerView: View, track: Track)
}
