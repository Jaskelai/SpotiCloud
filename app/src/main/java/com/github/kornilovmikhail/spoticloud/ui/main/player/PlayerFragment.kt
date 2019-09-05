package com.github.kornilovmikhail.spoticloud.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentPlayerBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import javax.inject.Inject

class PlayerFragment : BaseFragment() {

    companion object {

        fun getInstance() = PlayerFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player,
            container,
            false
        )
        binding.lifecycleOwner = this@PlayerFragment
        return binding.root
    }

    override fun injectViewModel() {
        playerViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
    }

    override fun subscribe() {
        lifecycle.addObserver(playerViewModel)

        playerViewModel.trackLiveData.observe(this, Observer { track ->
            println(track)
            binding.track = track
        })
    }
}
