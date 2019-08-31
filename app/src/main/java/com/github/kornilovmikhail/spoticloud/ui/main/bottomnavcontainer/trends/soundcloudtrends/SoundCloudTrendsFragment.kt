package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.utils.injectParentViewModel
import kotlinx.android.synthetic.main.fragment_soundcloud_trends.*
import javax.inject.Inject

class SoundCloudTrendsFragment : BaseFragment(), TrackClickListener {

    companion object {

        fun getInstance() = SoundCloudTrendsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @field:SoundCloudQualifier
    lateinit var trendsListAdapter: TrendsListAdapter

    private lateinit var trendsContainerViewModel: TrendsContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_soundcloud_trends, container, false)
    }

    override fun injectViewModel() {
        trendsContainerViewModel = injectParentViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun subscribe() {
        trendsContainerViewModel.soundcloudTracksLiveData.observe(this, Observer {
            trendsListAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        rv_trends_soundcloud.apply {
            adapter = trendsListAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onClick(track: Track?) {
    }
}
