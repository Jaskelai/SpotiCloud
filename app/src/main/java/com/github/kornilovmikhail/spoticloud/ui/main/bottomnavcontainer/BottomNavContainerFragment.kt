package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentBottomNavContainerBinding
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_bottom_nav_container.*
import javax.inject.Inject

class BottomNavContainerFragment : BaseFragment(), TrackClickListener {

    companion object {

        fun getInstance() = BottomNavContainerFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var bottomNavViewModel: BottomNavContainerViewModel
    private lateinit var binding: FragmentBottomNavContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBottomNavContainerBinding>(
            inflater,
            R.layout.fragment_bottom_nav_container,
            container,
            false
        ).apply {
            viewModel = bottomNavViewModel
            lifecycleOwner = this@BottomNavContainerFragment
        }
        return binding.root
    }

    override fun injectViewModel() {
        bottomNavViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupBottomBar()
        setupViewPager()
        setFooterClickListener()
    }

    override fun subscribe() {
        lifecycle.addObserver(bottomNavViewModel)

        bottomNavViewModel.isFooterEnabledLiveData.observe(this, Observer {
            binding.includeFooterPlayer.isExist = it
        })

        bottomNavViewModel.currentTrackLiveData.observe(this, Observer { track ->
            binding.includeFooterPlayer.track = track
        })
    }

    override fun onTrackClicked(track: Track?) {
        bottomNavViewModel.play(track)
    }

    private fun setupViewPager() {
        viewpager_bottom_nav.adapter = BottomNavContainerViewPager(childFragmentManager, lifecycle)

        viewpager_bottom_nav.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                bottom_nav_view.menu.getItem(position).isChecked = true
            }
        })

        viewpager_bottom_nav.setCurrentItem(BottomNavScreens.FAV_TRACKS_SCREEN.value, false)

        viewpager_bottom_nav.isUserInputEnabled = false
    }

    private fun setupBottomBar() {
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_action_search -> {
                    viewpager_bottom_nav.setCurrentItem(
                        BottomNavScreens.SEARCH_SCREEN.value,
                        false
                    )
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_action_tracks -> {
                    viewpager_bottom_nav.setCurrentItem(
                        BottomNavScreens.FAV_TRACKS_SCREEN.value,
                        false
                    )
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_action_trends -> {
                    viewpager_bottom_nav.setCurrentItem(
                        BottomNavScreens.TRENDS_SCREEN.value,
                        false
                    )
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setFooterClickListener() {
        include_footer_player.setOnClickListener {
            bottomNavViewModel.onFooterClicked()
        }
    }
}
