package com.example.fronteravictor_examenfinal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.fronteravictor_examenfinal.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabs

        val adapter = FragmetnsAdapter(this)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.fragment1)
                1 -> tab.text = getString(R.string.fragment2)
                2 -> tab.text = getString(R.string.fragment3)
            }

        }.attach()

        val prefe = PreferenceManager.getDefaultSharedPreferences(this)

        val changeColor = prefe.getBoolean("ChangeColor", true)

        if(changeColor)
            tabLayout.setBackgroundColor(resources.getColor(android.R.color.holo_green_light, theme))
        else
            tabLayout.setBackgroundColor(resources.getColor(android.R.color.white, theme))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.preferences) {
            startActivity(Intent(this, ActivityPreferences::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
//
//    }
//
//    override fun onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START))
//            drawerLayout.closeDrawer(GravityCompat.START)
//        else
//            super.onBackPressed()
//    }
}