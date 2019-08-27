package com.github.kornilovmikhail.spoticloud.ui.navigation.di

import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouterCiceroneImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

@Module
class CiceroneModule {

    @Provides
    @AppScope
    fun provideCiceroneRouter(): GlobalRouterCiceroneImpl =
        GlobalRouterCiceroneImpl()

    @Provides
    @AppScope
    fun provideCicerone(ciceroneRouter: GlobalRouterCiceroneImpl): Cicerone<GlobalRouterCiceroneImpl> {
        return Cicerone.create(ciceroneRouter)
    }

    @Provides
    @AppScope
    fun provideNavigatorHolder(cicerone: Cicerone<GlobalRouterCiceroneImpl>): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}
