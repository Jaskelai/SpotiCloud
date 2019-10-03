package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import io.reactivex.Observable
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

        observeSearchEditText()

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
    }

    private fun observeSearchEditText() {
        val observable = Observable.create<String> { emitter ->
            val watcher = object : TextWatcher {

                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    if (editable.isNullOrEmpty()) {
                        emitter.onNext("")
                    } else {
                        emitter.onNext(editable.toString())
                    }
                }

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            }

            emitter.setCancellable {
                et_search_track.removeTextChangedListener(watcher)
            }

            et_search_track.addTextChangedListener(watcher)
        }

        searchViewModel.searchForTracks(observable)
    }
}
