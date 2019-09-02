package com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.di

import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.di.BottomScreenScope
import com.github.kornilovmikhail.spoticloud.ui.main.bottomnavcontainer.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SearchBuilder {

    @BottomScreenScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    fun bindSearchFragment(): SearchFragment
}
