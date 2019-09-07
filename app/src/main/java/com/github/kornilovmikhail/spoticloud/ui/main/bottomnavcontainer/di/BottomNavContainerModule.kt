package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerFragment
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.BottomNavContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.di.ScreenScope
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.TrackClickListener
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class BottomNavContainerModule {

    @Module
    companion object {

        @Provides
        @ScreenScope
        @JvmStatic
        @BottomNavQualifier
        fun provideBottomFragmentManager(fragment: BottomNavContainerFragment): FragmentManager =
            fragment.childFragmentManager

        @Provides
        @ScreenScope
        @JvmStatic
        @BottomNavQualifier
        fun provideBottomLifecycle(fragment: BottomNavContainerFragment): Lifecycle =
            fragment.lifecycle
    }

    @Binds
    @ScreenScope
    abstract fun provideTrackClickListener(
        bottomNavContainerFragment: BottomNavContainerFragment
    ): TrackClickListener

    @Binds
    @IntoMap
    @ViewModelKey(BottomNavContainerViewModel::class)
    abstract fun provideBottomNavContainerViewModel(
        bottomNavContainerViewModel: BottomNavContainerViewModel
    ): ViewModel
}
