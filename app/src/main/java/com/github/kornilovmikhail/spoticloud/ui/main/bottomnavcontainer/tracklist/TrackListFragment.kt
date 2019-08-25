package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.tracklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import javax.inject.Inject

class TrackListFragment : BaseFragment() {

    companion object {

        fun getInstance() = TrackListFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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

    }

    override fun subscribe() {
        lifecycle.addObserver(viewModel)
    }
}
