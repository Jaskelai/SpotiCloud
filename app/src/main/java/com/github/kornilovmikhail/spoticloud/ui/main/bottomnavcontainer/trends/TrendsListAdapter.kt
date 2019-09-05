package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.TrendsListItemBinding
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackDiffUtilCallback

class TrendsListAdapter constructor(
    private val clickListener: TrackClickListener
) : ListAdapter<Track, TrendsListAdapter.TrackViewHolder>(TrackDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TrendsListItemBinding>(
            layoutInflater,
            R.layout.trends_list_item,
            parent,
            false
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.onBind(getItem(position), position, clickListener)
    }

    inner class TrackViewHolder(private val binding: TrendsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Track, position: Int, trackClickListener: TrackClickListener) {
            binding.track = item
            binding.position = position + 1
            binding.root.setOnClickListener {
                trackClickListener.onTrackClicked(item)
            }
            binding.executePendingBindings()
        }
    }
}
