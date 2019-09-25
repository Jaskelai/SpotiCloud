package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.trendssoundcloud

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
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSoundcloudTrendsBinding
import com.github.kornilovmikhail.spoticloud.di.SoundCloudQualifier
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsListAdapter
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_soundcloud_trends.*
import javax.inject.Inject

class TrendsSoundCloudFragment : BaseFragment() {

    companion object {

        fun getInstance() = TrendsSoundCloudFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    @field:SoundCloudQualifier
    lateinit var trendsListAdapter: TrendsListAdapter

    private lateinit var trendsSoundCloudViewModel: TrendsSoundCloudViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSoundcloudTrendsBinding>(
            inflater,
            R.layout.fragment_soundcloud_trends,
            container,
            false
        ).apply {
            viewModel = trendsSoundCloudViewModel
            lifecycleOwner = this@TrendsSoundCloudFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_trends_soundcloud.adapter = null
    }

    override fun injectViewModel() {
        trendsSoundCloudViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun subscribe() {
        lifecycle.addObserver(trendsSoundCloudViewModel)

        trendsSoundCloudViewModel.soundcloudTracksLiveData.observe(this, Observer {
            trendsListAdapter.submitList(it)
        })

        trendsSoundCloudViewModel.errorSoundCloudLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupRecyclerView() {
        rv_trends_soundcloud.apply {
            adapter = trendsListAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}
