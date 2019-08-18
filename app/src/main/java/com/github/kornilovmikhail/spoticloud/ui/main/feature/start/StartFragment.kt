package com.github.kornilovmikhail.spoticloud.ui.main.feature.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
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
    ): View? = inflater.inflate(R.layout.fragment_start, container, false)

    override fun injectViewModel() {
        startViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        btnStartAuthSoundcloud.setOnClickListener {
            startViewModel.onBtnAuthSoundcloudClick()
        }
    }

    override fun subscribe() {
        lifecycle.addObserver(startViewModel)
    }
}
