package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSearchBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import com.jakewharton.rxbinding3.widget.textChangeEvents
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    companion object {

        fun getInstance() = SearchFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var searchTracksAdapter: SearchTracksAdapter

    private lateinit var searchViewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        ).apply {
            viewModel = searchViewModel
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_search.adapter = null
    }

    override fun injectViewModel() {
        searchViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        setupRecyclerView()
        setupToolbar()
    }

    override fun subscribe() {
        lifecycle.addObserver(searchViewModel)

        searchViewModel.searchForTracks(et_search_track.textChangeEvents())

        searchViewModel.searchResultLiveData.observe(this, Observer {
            searchTracksAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        rv_search.apply {
            adapter = searchTracksAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupToolbar() {
        toolbar_text?.text = getString(R.string.search)

        if (parentFragment is BottomNavContainerFragment) {
            toolbar_btn_settings.setOnClickListener {
                (parentFragment as BottomNavContainerFragment).setToolbarClickListener()
            }
        }
    }
}
