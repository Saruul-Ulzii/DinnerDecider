package com.sw06d120.cv_builder_app.ui.main

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sw06d120.cv_builder_app.R

class MainScreen : AppCompatActivity() {
    private lateinit var tab: TabLayout
    private lateinit var pager: ViewPager
    private lateinit var bar: Toolbar

    private val tabIcons = intArrayOf(
        R.drawable.baseline_home_black_24dp,
        R.drawable.baseline_account_circle_black_24dp,
        R.drawable.baseline_work_black_24dp,
        R.drawable.baseline_contact_page_black_24dp
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab = findViewById(R.id.tabs)
        pager = findViewById(R.id.viewPager)
        bar = findViewById(R.id.toolbar)

        setSupportActionBar(bar)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FragmentHome(), "Home")
        adapter.addFragment(FragmentAboutMe(), "Me")
        adapter.addFragment(FragmentWork(), "Work")
        adapter.addFragment(FragmentContact(), "Contact")

        pager.adapter = adapter

        tab.setupWithViewPager(pager)

        tab.getTabAt(0)?.setIcon(tabIcons[0]);
        tab.getTabAt(1)?.setIcon(tabIcons[1]);
        tab.getTabAt(2)?.setIcon(tabIcons[2]);
        tab.getTabAt(3)?.setIcon(tabIcons[3]);
    }
}
