package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendsspotify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSpotifyTrendsBinding
import com.github.kornilovmikhail.spoticloud.di.SpotifyQualifier
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_spotify_trends.*
import javax.inject.Inject

class TrendsSpotifyFragment : BaseFragment() {

    companion object {

        fun getInstance() = TrendsSpotifyFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @field:SpotifyQualifier
    lateinit var trendsListAdapter: TrendsListAdapter

    private lateinit var trendsSpotifyViewModel: TrendsSpotifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSpotifyTrendsBinding>(
            inflater,
            R.layout.fragment_spotify_trends,
            container,
            false
        ).apply {
            viewModel = trendsSpotifyViewModel
            lifecycleOwner = this@TrendsSpotifyFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_trends_spotify.adapter = null
    }

    override fun injectViewModel() {
        trendsSpotifyViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun subscribe() {
        lifecycle.addObserver(trendsSpotifyViewModel)

        trendsSpotifyViewModel.spotifyTracksLiveData.observe(this, Observer {
            trendsListAdapter.submitList(it)
        })

        trendsSpotifyViewModel.errorSpotifyLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
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
