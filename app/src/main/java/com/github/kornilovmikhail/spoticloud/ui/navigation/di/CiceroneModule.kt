package com.github.kornilovmikhail.spoticloud.ui.navigation.di

import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.main.di.MainActivityScope
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.RouterCiceroneImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
class CiceroneModule {

    @Provides
    @MainActivityScope
    fun provideCiceroneRouter(): RouterCiceroneImpl = RouterCiceroneImpl()

    @Provides
    @MainActivityScope
    fun provideCicerone(ciceroneRouter: RouterCiceroneImpl): Cicerone<RouterCiceroneImpl> =
        Cicerone.create(ciceroneRouter)

    @Provides
    @MainActivityScope
    fun provideNavigatorHolder(cicerone: Cicerone<RouterCiceroneImpl>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @MainActivityScope
    fun provideNavigator(mainActivity: MainActivity): Navigator =
        SupportAppNavigator(mainActivity, mainActivity.getContainerId())
}
