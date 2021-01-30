package com.twitter.challenge.ui.view

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.twitter.challenge.R
import com.twitter.challenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTabs()
    }

    private fun setupTabs() {
        val tabLayout = binding.tabLayout
        val demoCollectionAdapter = MainTabAdapter(this)
        binding.pager.adapter = demoCollectionAdapter
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            tab.text = getString(demoCollectionAdapter.getTitle(position))
        }.attach()
    }
}

private class MainTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val items = arrayListOf(CurrentWeatherFragment(), FutureWeatherFragment())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

    @StringRes fun getTitle(position: Int): Int {
        return if (items[position] is CurrentWeatherFragment) {
            R.string.current_weather_title
        } else {
            R.string.future_weather_title
        }
    }
}