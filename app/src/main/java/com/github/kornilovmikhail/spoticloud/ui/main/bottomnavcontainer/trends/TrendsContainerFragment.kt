package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentTrendsContainerBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_trends_container.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class TrendsContainerFragment : BaseFragment() {

    companion object {

        fun getInstance() = TrendsContainerFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trendsContainerViewModel: TrendsContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTrendsContainerBinding>(
            inflater,
            R.layout.fragment_trends_container,
            container,
            false
        ).apply {
            viewModel = trendsContainerViewModel
            lifecycleOwner = this@TrendsContainerFragment
        }
        return binding.root
    }

    override fun injectViewModel() {
        trendsContainerViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupViewPager()
        setupToolbar()
    }

    override fun subscribe() {
        lifecycle.addObserver(trendsContainerViewModel)
    }

    private fun setupViewPager() {
        viewpager_trends.adapter = FragmentViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabs_trends, viewpager_trends,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                when (position) {
                    FragmentViewPagerAdapter.SOUNDCLOUD_TAB -> tab.text =
                        getString(R.string.soundcloud)
                    FragmentViewPagerAdapter.SPOTIFY_TAB -> tab.text = getString(R.string.spotify)
                    else -> {
                    }
                }
            }).attach()
    }

    private fun setupToolbar() {
        toolbar_text?.text = getString(R.string.trends)
    }
}
