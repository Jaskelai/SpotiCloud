package com.github.kornilovmikhail.spoticloud.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseActivity
import com.github.kornilovmikhail.spoticloud.utils.injectViewModel
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigatorHolder: NavigatorHolder

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val childFragmentManager =
            supportFragmentManager.fragments.firstOrNull()?.childFragmentManager
        childFragmentManager?.let {
            if (it.backStackEntryCount > 1) {
                it.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun injectViewModel() {
        mainViewModel = injectViewModel(viewModelFactory)
    }

    override fun subscribe() {
        lifecycle.addObserver(mainViewModel)
    }

    fun getContainerId(): Int = R.id.container
}
