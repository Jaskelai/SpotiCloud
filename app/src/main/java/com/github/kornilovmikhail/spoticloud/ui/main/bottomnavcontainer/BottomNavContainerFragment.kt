package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_bottom_nav_container.*
import javax.inject.Inject

class BottomNavContainerFragment : BaseFragment() {

    companion object {

        fun getInstance() = BottomNavContainerFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: BottomNavContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_nav_container, container, false)
    }

    override fun injectViewModel() {
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_action_search -> viewModel.onSearchBottomBtnClicked()
                R.id.bottom_action_tracks -> viewModel.onTrackListBtnClicked()
                R.id.bottom_action_trends -> viewModel.onTrendsBtnClicked()
            }
            true
        }
    }

    override fun subscribe() {
        lifecycle.addObserver(viewModel)

        viewModel.selecteditemLiveData.observe(this, Observer {
            when (it) {
                BottomNavContainerViewModel.SEARCH_ITEM -> {
                    bottom_nav_view.selectedItemId = R.id.bottom_action_search
                }
                BottomNavContainerViewModel.TRACK_LIST_ITEM -> {
                    bottom_nav_view.selectedItemId = R.id.bottom_action_tracks
                }
                BottomNavContainerViewModel.TRENDS_ITEM -> {
                    bottom_nav_view.selectedItemId = R.id.bottom_action_trends
                }
                else -> {}
            }
        })
    }
}
