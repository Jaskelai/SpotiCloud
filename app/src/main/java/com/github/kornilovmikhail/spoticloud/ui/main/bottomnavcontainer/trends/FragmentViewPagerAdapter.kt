package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.SoundCloudTrendsFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.SpotifyTrendsFragment

class FragmentViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val SOUNDCLOUD_TAB = 0
        const val SPOTIFY_TAB = 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            SOUNDCLOUD_TAB -> SoundCloudTrendsFragment.getInstance()
            SPOTIFY_TAB -> SpotifyTrendsFragment.getInstance()
            else -> {
                SoundCloudTrendsFragment.getInstance()
            }
        }
    }

    override fun getItemCount(): Int = 2
}
