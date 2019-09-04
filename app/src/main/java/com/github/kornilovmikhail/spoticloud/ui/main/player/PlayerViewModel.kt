package com.github.kornilovmikhail.spoticloud.ui.main.player

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kornilovmikhail.spoticloud.domain.model.Track
import javax.inject.Inject

class PlayerViewModel @Inject constructor(

) : ViewModel(), LifecycleObserver {

    val trackLiveData = MutableLiveData<Track>()
}
