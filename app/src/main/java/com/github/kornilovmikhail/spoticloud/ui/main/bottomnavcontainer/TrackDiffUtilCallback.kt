package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.recyclerview.widget.DiffUtil
import com.github.kornilovmikhail.spoticloud.domain.model.Track

class TrackDiffUtilCallback : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem.id == newItem.id) && (oldItem.title == newItem.title)
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem.title == newItem.title) && (oldItem.artworkUrl == newItem.artworkUrl)
                && (oldItem.author == newItem.author)
    }
}
