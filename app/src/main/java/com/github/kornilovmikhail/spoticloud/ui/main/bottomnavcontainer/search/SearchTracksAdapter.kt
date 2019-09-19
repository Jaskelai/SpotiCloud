package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.SearchListItemBinding
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackDiffUtilCallback
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.popupmenu.PopupMenuDelegate
import javax.inject.Inject

class SearchTracksAdapter @Inject constructor(
    private val clickListener: TrackClickListener,
    private val popupMenuDelegate: PopupMenuDelegate
) : ListAdapter<Track, SearchTracksAdapter.SearchViewHolder>(TrackDiffUtilCallback()),
    PopupMenuDelegate by popupMenuDelegate {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SearchListItemBinding>(
            layoutInflater,
            R.layout.search_list_item,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    inner class SearchViewHolder(private val binding: SearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Track, trackClickListener: TrackClickListener) {
            binding.track = item
            binding.root.setOnClickListener {
                trackClickListener.onTrackClicked(item)
            }
            binding.root.setOnLongClickListener {
                showPopup(binding.root.context, binding.root, item)
                true
            }
            binding.executePendingBindings()
        }
    }
}
