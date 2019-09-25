package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_trends_container.*
import kotlinx.android.synthetic.main.toolbar.*

class TrendsContainerFragment : BaseFragment() {

    companion object {

        fun getInstance() = TrendsContainerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trends_container, container, false)
    }

    override fun injectViewModel() {
    }

    override fun setupViews() {
        setupViewPager()
        setupToolbar()
    }

    override fun subscribe() {
    }

    private fun setupViewPager() {
        viewpager_trends.adapter = TrendsViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabs_trends, viewpager_trends,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                when (position) {
                    TrendsScreens.SOUNDCLOUD.value -> tab.text =
                        getString(R.string.soundcloud)
                    TrendsScreens.SPOTIFY.value -> tab.text = getString(R.string.spotify)
                    else -> {
                    }
                }
            }).attach()
    }

    private fun setupToolbar() {
        toolbar_text?.text = getString(R.string.trends)
    }
}
