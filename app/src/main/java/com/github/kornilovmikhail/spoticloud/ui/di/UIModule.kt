package com.github.kornilovmikhail.spoticloud.ui.di

import com.github.kornilovmikhail.spoticloud.ui.main.di.MainActivityBuilder
import com.github.kornilovmikhail.spoticloud.ui.navigation.di.CiceroneModule
import dagger.Module

@Module(includes = [CiceroneModule::class, ViewModelModule::class, MainActivityBuilder::class])
class UIModule
