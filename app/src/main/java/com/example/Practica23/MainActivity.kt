package com.example.Practica23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.Practica23.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = PagerStateAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Fragment ${(position + 1)}"
        }.attach()

    }
}