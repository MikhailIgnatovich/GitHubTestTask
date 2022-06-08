package com.bulich.misha.githubtesttask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bulich.misha.githubtesttask.R
import com.bulich.misha.githubtesttask.presentation.fragments.HomeScreenFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, HomeScreenFragment.newInstance())
                .commit()
        }

    }
}