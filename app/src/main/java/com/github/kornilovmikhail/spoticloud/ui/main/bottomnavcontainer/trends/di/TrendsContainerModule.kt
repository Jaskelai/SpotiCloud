package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.trends.TrendsContainerFragment
import dagger.Module
import dagger.Provides

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
}
