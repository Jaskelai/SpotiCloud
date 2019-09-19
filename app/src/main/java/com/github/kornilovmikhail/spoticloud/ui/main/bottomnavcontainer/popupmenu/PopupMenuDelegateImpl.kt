package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.popupmenu

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.domain.interactors.AddToFavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopupMenuDelegateImpl @Inject constructor(
    private val addToFavTracksUseCase: AddToFavTracksUseCase,
    private val appContext: Context
) : PopupMenuDelegate {

    private val disposables = CompositeDisposable()

    override fun showPopup(
        context: Context,
        containerView: View,
        track: Track
    ) {
        val popup = PopupMenu(context, containerView)
        popup.apply {
            inflate(R.menu.long_click_track_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_search_popup_add_fav -> {
                        addTrackToFav(track)
                        return@setOnMenuItemClickListener true
                    }
                }
                false
            }
            show()
        }
    }

    private fun addTrackToFav(track: Track) {
        disposables.add(
            addToFavTracksUseCase.addToFavTracks(track)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(
                        appContext,
                        appContext.resources.getText(R.string.track_added),
                        Toast.LENGTH_SHORT
                    ).show()
                }, {
                    Toast.makeText(
                        appContext,
                        appContext.resources.getText(R.string.track_not_added),
                        Toast.LENGTH_SHORT
                    ).show()
                })
        )
    }
}
