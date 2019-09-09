package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.soundcloudtrends.SoundCloudTrendsFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.spotifytrends.SpotifyTrendsFragment
import javax.inject.Inject

class TrendsViewPagerAdapter @Inject constructor(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TrendsScreens.SOUNDCLOUD.value -> SoundCloudTrendsFragment.getInstance()
            TrendsScreens.SPOTIFY.value -> SpotifyTrendsFragment.getInstance()
            else -> { SoundCloudTrendsFragment.getInstance() }
        }
    }

    override fun getItemCount(): Int = TrendsScreens.values().size
}

enum class TrendsScreens(val value: Int) {
    SOUNDCLOUD(0), SPOTIFY(1)
}
