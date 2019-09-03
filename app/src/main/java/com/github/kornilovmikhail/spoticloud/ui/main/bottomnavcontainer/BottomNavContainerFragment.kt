package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.LayoutFooterPlayerBinding
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomNavQualifier
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_bottom_nav_container.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class BottomNavContainerFragment : BaseFragment(), TrackClickListener {

    companion object {

        fun getInstance() = BottomNavContainerFragment()
    }

    @Inject
    @field:BottomNavQualifier
    lateinit var navigator: Navigator

    @Inject
    @field:BottomNavQualifier
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: BottomNavContainerViewModel

    private var footer: View? = null
    private var footerBinding: LayoutFooterPlayerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_nav_container, container, false)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun injectViewModel() {
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupBottomBar()
        setupFooterInflationListener()
    }

    override fun subscribe() {
        lifecycle.addObserver(viewModel)

        viewModel.selectedItemLiveData.observe(this, Observer {
            if (it == BottomNavContainerViewModel.FAV_TRACKS_SCREEN) {
                bottom_nav_view.selectedItemId = R.id.bottom_action_tracks
            }
        })

        viewModel.currentTrackLiveData.observe(this, Observer { track ->
            if (track != null) {
                if (footer == null) {
                    footer = vs_footer_player.inflate()
                }
                footerBinding?.track = track
            }
        })
    }

    override fun onClick(track: Track?) {
        viewModel.play(track)
    }

    fun getContainerId(): Int = R.id.container_bottom_nav

    private fun setupBottomBar() {
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_action_search -> {
                    viewModel.onSearchBottomBtnClicked()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_action_tracks -> {
                    viewModel.onTrackListBtnClicked()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_action_trends -> {
                    viewModel.onTrendsBtnClicked()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setupFooterInflationListener() {
        vs_footer_player.setOnInflateListener { viewStub, view ->
            footerBinding = DataBindingUtil.bind(view)
        }
    }
}
