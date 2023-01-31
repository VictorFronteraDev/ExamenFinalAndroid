package com.example.fronteravictor_examenfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference


class PreferencesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        val pref = findPreference<SwitchPreference>("ChangeColor")

        pref?.setOnPreferenceChangeListener { preference, newValue ->


            true
        }

    }
}