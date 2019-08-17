package com.github.kornilovmikhail.spoticloud.ui.main

import android.os.Bundle
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.base.BaseActivity
import com.github.kornilovmikhail.spoticloud.ui.feature.start.StartFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, StartFragment()).commit()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun subscribe() {

    }

    fun getContainerId(): Int = R.id.container
}
