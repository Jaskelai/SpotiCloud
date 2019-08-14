package com.github.kornilovmikhail.spoticloud.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.kornilovmikhail.spoticloud.R
import com.github.kornilovmikhail.spoticloud.ui.feature.start.StartFragment
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, StartFragment.getInstance())
            .commit()
    }
}
