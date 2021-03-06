package com.github.kornilovmikhail.spoticloud.ui.main.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentStartBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : BaseFragment() {

    companion object {

        fun getInstance() = StartFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var startViewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(
            inflater,
            R.layout.fragment_start,
            container,
            false
        )
        binding.lifecycleOwner = this@StartFragment
        binding.viewModel = startViewModel
        return binding.root
    }

    override fun injectViewModel() {
        startViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        btn_soundcloud_start.setOnClickListener {
            startViewModel.onBtnAuthSoundcloudClicked()
        }
        btn_spotify_start.setOnClickListener {
            startViewModel.onBtnAuthSpotifyClicked()
        }
    }

    override fun subscribe() {
        lifecycle.addObserver(startViewModel)

        startViewModel.authedLiveData.observe(this, Observer {
            if (it) showSnackbar()
        })
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(layout_start, "", Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.go_to_app) {
                startViewModel.onBtnSnackbarClicked()
            }
        context?.let {
            snackbar.setActionTextColor(ContextCompat.getColor(it, R.color.color_white))
            snackbar.view.setBackgroundColor(
                ContextCompat.getColor(
                    it,
                    R.color.color_soundcloud_auth_buttons
                )
            )
        }
        snackbar.show()
    }
}
