package com.github.kornilovmikhail.spoticloud.ui.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.kornilovmikhail.spoticloud.ui.main.MainActivity
import com.github.kornilovmikhail.spoticloud.ui.main.MainViewModel
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.Router
import com.github.kornilovmikhail.spoticloud.ui.navigation.router.RouterCiceroneImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
abstract class MainActivityModule {

    @Module
    companion object {

        @Provides
        @MainActivityScope
        @JvmStatic
        fun provideNavigator(mainActivity: MainActivity): Navigator {
            return SupportAppNavigator(mainActivity, mainActivity.getContainerId())
        }
    }

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @MainActivityScope
    abstract fun provideRouter(router: RouterCiceroneImpl): Router
}
