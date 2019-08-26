package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.tracklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.jaskelai.spoticloud.R
import com.github.jaskelai.spoticloud.databinding.TrackListItemBinding
import com.github.jaskelai.spoticloud.domain.model.Track
import javax.inject.Inject

class TrackListAdapter @Inject constructor(
    private val clickListener: TrackListClickListener
) : ListAdapter<Track, TrackListAdapter.TrackViewHolder>(TrackDiffUtilCallback()) {

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

        fun onBind(item: Track, trackListClickListener: TrackListClickListener) {
            binding.track = item
            binding.executePendingBindings()
        }
    }
}

class TrackDiffUtilCallback : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem.id == newItem.id) && (oldItem.title == newItem.title)
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem.title == newItem.title) && (oldItem.artworkUrl == newItem.artworkUrl)
                && (oldItem.author == newItem.author)
    }
}
