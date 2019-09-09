package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class TrendsContainerModule {

    @Module
    companion object {

        @Provides
        @BottomScreenScope
        @JvmStatic
        fun provideTrendsFragmentManager(fragment: TrendsContainerFragment): FragmentManager =
            fragment.childFragmentManager

        @Provides
        @BottomScreenScope
        @JvmStatic
        fun provideTrendsLifecycle(fragment: TrendsContainerFragment): Lifecycle =
            fragment.lifecycle
    }

    @Binds
    @IntoMap
    @ViewModelKey(TrendsContainerViewModel::class)
    abstract fun provideTrendsContainerViewModel(trackContainerViewModel: TrendsContainerViewModel): ViewModel
}
