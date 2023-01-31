package com.example.fronteravictor_examenfinal

import android.R
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.google.android.material.tabs.TabLayout

class ActivityPreferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, PreferencesFragment())
            .commit()
    }
}