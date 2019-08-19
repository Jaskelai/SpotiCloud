package com.github.kornilovmikhail.spoticloud.ui.main.feature.soundcloudauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSoundcloudAuthBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_soundcloud_auth.*
import javax.inject.Inject

class SoundcloudAuthFragment : BaseFragment() {

    companion object {

        fun getInstance() = SoundcloudAuthFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var soundcloudAuthViewModel: SoundcloudAuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSoundcloudAuthBinding>(
            inflater,
            R.layout.fragment_soundcloud_auth,
            container,
            false
        ).apply {
            viewModel = soundcloudAuthViewModel
            lifecycleOwner = this@SoundcloudAuthFragment
        }
        return binding.root
    }

    override fun injectViewModel() {
        soundcloudAuthViewModel = injectViewModel(viewModelFactory)
    }

    override fun setupViews() {
        btn_back_soundcloud_auth.setOnClickListener {
            soundcloudAuthViewModel.onBackButtonClicked()
        }
        btn_signin_soundcloud.setOnClickListener {
            val email = et_email_soundcloud_auth.text.toString()
            val password = et_password_soundcloud_auth.text.toString()
            soundcloudAuthViewModel.onSigninButtonClicked(email, password)
        }
    }

    override fun subscribe() {
        lifecycle.addObserver(soundcloudAuthViewModel)
    }
}
