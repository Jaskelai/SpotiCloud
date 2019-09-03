package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.di

import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.ui.di.ViewModelKey
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}
