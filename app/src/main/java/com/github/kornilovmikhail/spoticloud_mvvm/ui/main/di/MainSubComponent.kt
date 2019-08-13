package com.github.kornilovmikhail.spoticloud_mvvm.ui.main.di

import com.github.kornilovmikhail.spoticloud_mvvm.di.scope.MainScope
import dagger.Subcomponent

@MainScope
@Subcomponent
interface MainSubComponent {

    @Subcomponent.Factory
    interface Factory {

    }
}
