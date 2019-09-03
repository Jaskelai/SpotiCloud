package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.utils.injectParentViewModel
import kotlinx.android.synthetic.main.fragment_spotify_trends.*
import javax.inject.Inject

class SpotifyTrendsFragment : BaseFragment() {

    companion object {

        fun getInstance() = SpotifyTrendsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @field:SpotifyQualifier
    lateinit var trendsListAdapter: TrendsListAdapter

    private lateinit var trendsContainerViewModel: TrendsContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spotify_trends, container, false)
    }

    override fun injectViewModel() {
        trendsContainerViewModel = injectParentViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun subscribe() {
        trendsContainerViewModel.spotifyTracksLiveData.observe(this, Observer {
            trendsListAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        rv_trends_spotify.apply {
            adapter = trendsListAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}
