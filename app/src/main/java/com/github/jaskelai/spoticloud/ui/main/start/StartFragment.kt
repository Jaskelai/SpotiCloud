package com.github.jaskelai.spoticloud.ui.main.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.jaskelai.spoticloud.R
import com.github.jaskelai.spoticloud.databinding.FragmentStartBinding
import com.github.jaskelai.spoticloud.ui.base.BaseFragment
import com.github.jaskelai.spoticloud.utils.injectViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : BaseFragment() {

    companion object {

        fun getInstance() = StartFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        ).apply {
            viewModel = startViewModel
            lifecycleOwner = this@StartFragment
        }
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
            startViewModel.onBtnAuthSpotifyClicked(this)
        }
    }

    override fun subscribe() {
        lifecycle.addObserver(startViewModel)

        startViewModel.authedLiveData.observe(this, Observer {
            if (it) showSnackbar()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            startViewModel.handleResult(requestCode, data)
        }
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
