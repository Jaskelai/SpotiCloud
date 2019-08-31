package com.github.kornilovmikhail.spoticloud.ui.navigation.di

import com.github.kornilovmikhail.spoticloud.di.scope.AppScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomNavQualifier
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouterCiceroneImpl
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouterCiceroneImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

@Module
class CiceroneModule {

    @Provides
    @AppScope
    fun provideLocalRouterCiceroneImpl(): LocalBottomNavRouterCiceroneImpl {
        return LocalBottomNavRouterCiceroneImpl()
    }

    @Provides
    @AppScope
    @BottomNavQualifier
    fun provideLocalCicerone(
        localCiceroneRouter: LocalBottomNavRouterCiceroneImpl
    ): Cicerone<LocalBottomNavRouterCiceroneImpl> {
        return Cicerone.create(localCiceroneRouter)
    }

    @Provides
    @AppScope
    @BottomNavQualifier
    fun provideLocalNavigatorHolder(
        @BottomNavQualifier cicerone: Cicerone<LocalBottomNavRouterCiceroneImpl>
    ): NavigatorHolder {
        return cicerone.navigatorHolder
    }

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
