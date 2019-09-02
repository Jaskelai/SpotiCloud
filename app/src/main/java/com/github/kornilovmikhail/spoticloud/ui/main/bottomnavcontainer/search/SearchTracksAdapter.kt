package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

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
import javax.inject.Inject

class SearchTracksAdapter @Inject constructor(
    private val clickListener: TrackClickListener
) : ListAdapter<Track, SearchTracksAdapter.SearchViewHolder>(TrackDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TrackListItemBinding>(
            layoutInflater,
            R.layout.track_list_item,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    inner class SearchViewHolder(private val binding: TrackListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Track, trackClickListener: TrackClickListener) {
            binding.track = item
            binding.executePendingBindings()
        }
    }
}
