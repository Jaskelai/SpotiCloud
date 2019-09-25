package com.github.kornilovmikhail.spoticloud.ui.main.spotifyauth

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.databinding.FragmentSpotifyAuthBinding
import com.github.kornilovmikhail.spoticloud.ui.base.BaseFragment
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_spotify_auth.*
import javax.inject.Inject

class SpotifyAuthFragment : BaseFragment() {

    companion object {
        private const val CODE = "code"

        fun getInstance() = SpotifyAuthFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var spotifyAuthViewModel: SpotifyAuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSpotifyAuthBinding>(
            inflater,
            R.layout.fragment_spotify_auth,
            container,
            false
        ).apply {
            viewModel = spotifyAuthViewModel
            lifecycleOwner = this@SpotifyAuthFragment
        }
        return binding.root
    }

    override fun injectViewModel() {
        spotifyAuthViewModel = injectViewModel(viewModelFactory)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupViews() {
        webview_spotify_auth.settings.javaScriptEnabled = true

        setupWebView()
    }

    override fun subscribe() {
        lifecycle.addObserver(spotifyAuthViewModel)

        spotifyAuthViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupWebView() {
        spotifyAuthViewModel.showProgressBar()
        webview_spotify_auth.loadUrl(spotifyAuthViewModel.getAuthUrl())

        webview_spotify_auth.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                url?.let {
                    val code = Uri.parse(url).getQueryParameter(CODE)
                    spotifyAuthViewModel.onPageLoaded(code)
                }
            }

            @SuppressWarnings("deprecation")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    return dealWithUrl(Uri.parse(url))
                }
                return true
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                request?.let {
                    return dealWithUrl(it.url)
                }
                return true
            }

            private fun dealWithUrl(url: Uri): Boolean {
                val code = url.getQueryParameter(CODE)
                spotifyAuthViewModel.onPageLoading(code)
                if (code != null) {
                    return true
                }
                return false
            }
        }
    }
}
