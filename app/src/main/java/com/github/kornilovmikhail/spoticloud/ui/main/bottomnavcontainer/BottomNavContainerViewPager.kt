package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomNavQualifier
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.FavTracksFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.SearchFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment
import javax.inject.Inject

class BottomNavContainerViewPager @Inject constructor(
    @BottomNavQualifier fragmentManager: FragmentManager,
    @BottomNavQualifier lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val SEARCH_SCREEN = 0
        const val FAV_TRACKS_SCREEN = 1
        const val TRENDS_SCREEN = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            SEARCH_SCREEN -> SearchFragment.getInstance()
            FAV_TRACKS_SCREEN -> FavTracksFragment.getInstance()
            TRENDS_SCREEN -> TrendsContainerFragment.getInstance()
            else -> { FavTracksFragment.getInstance() }
        }
    }

    override fun getItemCount(): Int = 3
}
