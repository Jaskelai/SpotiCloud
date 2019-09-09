package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks.FavTracksFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.SearchFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment

class BottomNavContainerViewPager (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            BottomNavScreens.SEARCH_SCREEN.value -> SearchFragment.getInstance()
            BottomNavScreens.FAV_TRACKS_SCREEN.value -> FavTracksFragment.getInstance()
            BottomNavScreens.TRENDS_SCREEN.value -> TrendsContainerFragment.getInstance()
            else -> { FavTracksFragment.getInstance() }
        }
    }

    override fun getItemCount(): Int = BottomNavScreens.values().size
}

enum class BottomNavScreens(val value: Int){
    SEARCH_SCREEN(0), FAV_TRACKS_SCREEN(1), TRENDS_SCREEN(2)
}
