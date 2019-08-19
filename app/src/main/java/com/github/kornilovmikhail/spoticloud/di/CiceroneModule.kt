package com.github.kornilovmikhail.spoticloud.di

import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.RouterCiceroneImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

@Module
class CiceroneModule {

    @Provides
    @AppScope
    fun provideCiceroneRouter(): RouterCiceroneImpl = RouterCiceroneImpl()

    @Provides
    @AppScope
    fun provideCicerone(ciceroneRouter: RouterCiceroneImpl): Cicerone<RouterCiceroneImpl> =
        Cicerone.create(ciceroneRouter)

    @Provides
    @AppScope
    fun provideNavigatorHolder(cicerone: Cicerone<RouterCiceroneImpl>): NavigatorHolder = cicerone.navigatorHolder
}
