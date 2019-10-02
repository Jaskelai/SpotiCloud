package com.github.kornilovmikhail.spoticloud.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSettingsBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    companion object {

        fun getInstance() = SettingsFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        ).apply {
            viewModel = settingsViewModel
            lifecycleOwner = this@SettingsFragment
        }
        return binding.root
    }

    override fun injectViewModel() {
        settingsViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {

    }

    override fun subscribe() {
        lifecycle.addObserver(settingsViewModel)
    }
}
