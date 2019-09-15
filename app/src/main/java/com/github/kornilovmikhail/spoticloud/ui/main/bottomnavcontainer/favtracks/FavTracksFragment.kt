package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.favtracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentFavTracksBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerViewModel
import com.github.kornilovmikhail.spoticloud.utils.injectParentViewModel
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_fav_tracks.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FavTracksFragment : BaseFragment(){

    companion object {

        fun getInstance() = FavTracksFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var favTracksAdapter: FavTracksAdapter

    private lateinit var favTracksViewModel: FavTracksViewModel
    private lateinit var bottomNavContainerViewModel: BottomNavContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFavTracksBinding>(
            inflater,
            R.layout.fragment_fav_tracks,
            container,
            false
        ).apply {
            viewModel = favTracksViewModel
            lifecycleOwner = this@FavTracksFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_list_tracks.adapter = null
    }

    override fun injectViewModel() {
        favTracksViewModel = injectViewModel(viewModelFactory)
        bottomNavContainerViewModel = injectParentViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
        setupToolbar()
    }

    override fun subscribe() {
        lifecycle.addObserver(favTracksViewModel)

        favTracksViewModel.trackListLiveData.observe(this, Observer {
            favTracksAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        rv_list_tracks.apply {
            adapter = favTracksAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupToolbar() {
        toolbar_text?.text = getString(R.string.favourite_tracks)
    }
}
