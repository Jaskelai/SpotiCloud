package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_track_list.*
import javax.inject.Inject

class TrackListFragment : BaseFragment(), TrackListClickListener {

    companion object {

        fun getInstance() = TrackListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var trackListAdapter: TrackListAdapter

    private lateinit var viewModel: TrackListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_list, container, false)
    }

    override fun injectViewModel() {
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun subscribe() {
        lifecycle.addObserver(viewModel)

        viewModel.trackListLiveData.observe(this, Observer {
            trackListAdapter.submitList(it)
        })
    }

    override fun onClick(track: Track?) {

    }

    private fun setupRecyclerView() {
        rv_list_tracks.adapter = trackListAdapter
        rv_list_tracks.setHasFixedSize(true)
        rv_list_tracks.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }
}
