package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.popupmenu

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.domain.interactors.AddToFavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.interactors.DeleteFromFavTracksUseCase
import com.github.kornilovmikhail.spoticloud.domain.interfaces.FavTracksPopupMenuDelegate
import com.github.kornilovmikhail.spoticloud.domain.interfaces.SearchTrendsPopupMenuDelegate
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

typealias DeleteListener = () -> Unit

class PopupMenuDelegateImpl @Inject constructor(
    private val addToFavTracksUseCase: AddToFavTracksUseCase,
    private val deleteFromFavTracksUseCase: DeleteFromFavTracksUseCase,
    private val appContext: Context
) : SearchTrendsPopupMenuDelegate,
    FavTracksPopupMenuDelegate {

    private val disposables = CompositeDisposable()

    override fun showSearchTrendsPopup(containerView: View, track: Track) {
        val popup = PopupMenu(containerView.context, containerView)
        popup.apply {
            inflate(R.menu.long_click_search_trends_track_menu)
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

    override fun showFavTracksPopup(
        containerView: View,
        track: Track,
        deleteListener: DeleteListener
    ) {
        val popup = PopupMenu(containerView.context, containerView)
        popup.apply {
            inflate(R.menu.long_click_fav_tracks_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_search_popup_delete_fav -> {
                        deleteTrackFromFav(track, deleteListener)
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

    private fun deleteTrackFromFav(track: Track, deleteListener: DeleteListener) {
        disposables.add(
            deleteFromFavTracksUseCase.deleteTrackFromFav(track)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    deleteListener.invoke()
                    Toast.makeText(
                        appContext,
                        appContext.resources.getText(R.string.track_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }, {
                    Toast.makeText(
                        appContext,
                        appContext.resources.getText(R.string.track_not_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                })
        )
    }
}
