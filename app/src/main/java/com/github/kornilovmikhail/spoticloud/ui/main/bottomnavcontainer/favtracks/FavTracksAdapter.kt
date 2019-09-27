package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.TrackListItemBinding
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackDiffUtilCallback
import com.github.kornilovmikhail.spoticloud.domain.interfaces.FavTracksPopupMenuDelegate
import javax.inject.Inject

class FavTracksAdapter @Inject constructor(
    private val clickListener: TrackClickListener,
    private val favTracksPopupMenuDelegate: FavTracksPopupMenuDelegate
) : ListAdapter<Track, FavTracksAdapter.TrackViewHolder>(TrackDiffUtilCallback()),
    FavTracksPopupMenuDelegate by favTracksPopupMenuDelegate {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TrackListItemBinding>(
            layoutInflater,
            R.layout.track_list_item,
            parent,
            false
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    inner class TrackViewHolder(private val binding: TrackListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Track, trackClickListener: TrackClickListener) {
            binding.track = item
            binding.root.setOnClickListener {
                trackClickListener.onTrackClicked(item)
            }
            binding.root.setOnLongClickListener {
                showFavTracksPopup(binding.root, item, this::deleteTrackFromList)
                true
            }
            binding.executePendingBindings()
        }

        private fun deleteTrackFromList() {
            notifyItemRemoved(adapterPosition)
        }
    }
}
