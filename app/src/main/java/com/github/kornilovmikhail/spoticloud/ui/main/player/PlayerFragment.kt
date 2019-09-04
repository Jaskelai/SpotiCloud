package com.github.kornilovmikhail.spoticloud.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import javax.inject.Inject

class PlayerFragment : BaseFragment() {

    companion object {

        fun getInstance() = PlayerFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun injectViewModel() {
        playerViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
    }

    override fun subscribe() {
        lifecycle.addObserver(playerViewModel)
    }
}
