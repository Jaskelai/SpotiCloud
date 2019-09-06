package com.github.kornilovmikhail.spoticloud.ui.main.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.main.MainViewModel
import com.github.kornilovmikhail.spoticloud.ui.navigation.SpotiCloudSupportAppNavigator
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouter
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.GlobalRouterCiceroneImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Navigator

@Module
abstract class MainActivityModule {

    @Module
    companion object {

        @Provides
        @MainActivityScope
        @JvmStatic
        fun provideNavigator(mainActivity: MainActivity): Navigator {
            return SpotiCloudSupportAppNavigator(mainActivity, mainActivity.getContainerId())
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @MainActivityScope
    abstract fun provideRouter(router: GlobalRouterCiceroneImpl): GlobalRouter
}
