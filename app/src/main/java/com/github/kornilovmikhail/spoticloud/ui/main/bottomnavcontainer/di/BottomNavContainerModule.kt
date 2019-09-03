package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouter
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.navigation.LocalBottomNavRouterCiceroneImpl
import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
abstract class BottomNavContainerModule {

    @Module
    companion object {

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
    @ScreenScope
    abstract fun provideTrackClickListener(
        bottomNavContainerFragment: BottomNavContainerFragment
    ): TrackClickListener

    @Binds
    @IntoMap
    @ViewModelKey(BottomNavContainerViewModel::class)
    abstract fun provideNavContainerViewModel(
        bottomNavContainerViewModel: BottomNavContainerViewModel
    ): ViewModel
}
