package com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerViewModel
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import com.github.jaskelai.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouterCiceroneImpl
import com.github.jaskelai.spoticloud.ui.main.di.ScreenScope
import com.github.jaskelai.spoticloud.ui.main.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
abstract class BottomNavContainerModule {

    @Module
    companion object {

        @Provides
        @ScreenScope
        @JvmStatic
        fun provideLocalRouterCiceroneImpl(): LocalBottomNavRouterCiceroneImpl {
            return LocalBottomNavRouterCiceroneImpl()
        }

        @Provides
        @ScreenScope
        @BottomNavQualifier
        @JvmStatic
        fun provideCicerone(
            localCiceroneRouter: LocalBottomNavRouterCiceroneImpl
        ): Cicerone<LocalBottomNavRouterCiceroneImpl> {
            return Cicerone.create(localCiceroneRouter)
        }

        @Provides
        @ScreenScope
        @BottomNavQualifier
        @JvmStatic
        fun provideNavigatorHolder(
            @BottomNavQualifier cicerone: Cicerone<LocalBottomNavRouterCiceroneImpl>
        ): NavigatorHolder {
            return cicerone.navigatorHolder
        }

        @Provides
        @ScreenScope
        @BottomNavQualifier
        @JvmStatic
        fun provideNavigator(bottomNavContainerFragment: BottomNavContainerFragment): Navigator {
            return SupportAppNavigator(
                bottomNavContainerFragment.activity,
                bottomNavContainerFragment.childFragmentManager,
                bottomNavContainerFragment.getContainerId()
            )
        }
    }

    @Binds
    @ScreenScope
    abstract fun provideLocalBottomNavRouter(
        localBottomNavRouter: LocalBottomNavRouterCiceroneImpl
    ): LocalBottomNavRouter

    @Binds
    @IntoMap
    @ViewModelKey(BottomNavContainerViewModel::class)
    abstract fun provideNavContainerViewModel(
        bottomNavContainerViewModel: BottomNavContainerViewModel
    ): ViewModel
}
