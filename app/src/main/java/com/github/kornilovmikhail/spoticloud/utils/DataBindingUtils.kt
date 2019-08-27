package com.github.kornilovmikhail.spoticloud.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.domain.model.StreamServiceEnum
import com.squareup.picasso.Picasso

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean?) {
    value?.let {
        view.visibility = if (it) View.VISIBLE else View.INVISIBLE
    }
}

@BindingAdapter("android:imageUrl")
fun setImage(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.ic_music_note_black_32dp)
        .into(view)
}

@BindingAdapter("android:srcMusicResource")
fun setSrcResource(view: ImageView, streamServiceEnum: StreamServiceEnum) {
    when (streamServiceEnum) {
        StreamServiceEnum.SOUNDCLOUD -> view.setImageResource(R.drawable.ic_soundcloudlogo)
        StreamServiceEnum.SPOTIFY -> view.setImageResource(R.drawable.ic_spotifylogo)
    }
}
