package com.sw06d120.cv_builder_app

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sw06d120.cv_builder_app.ui.main.FragmentAboutMe
import com.sw06d120.cv_builder_app.ui.main.FragmentContact
import com.sw06d120.cv_builder_app.ui.main.FragmentHome
import com.sw06d120.cv_builder_app.ui.main.FragmentWork

class MainActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout // creating object of TabLayout
    private lateinit var bar: Toolbar // creating object of ToolBar

    private val tabIcons = intArrayOf(
        R.drawable.baseline_home_black_24dp,
        R.drawable.baseline_account_circle_black_24dp,
        R.drawable.baseline_work_black_24dp,
        R.drawable.baseline_contact_page_black_24dp
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set the references of the declared objects above
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
        bar = findViewById(R.id.toolbar)

        // To make our toolbar show the application
        // we need to give it to the ActionBar
        setSupportActionBar(bar)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(FragmentHome(), "Home")
        adapter.addFragment(FragmentAboutMe(), "Me")
        adapter.addFragment(FragmentWork(), "Work")
        adapter.addFragment(FragmentContact(), "Contact")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)

        tab.getTabAt(0)?.setIcon(tabIcons[0]);
        tab.getTabAt(1)?.setIcon(tabIcons[1]);
        tab.getTabAt(2)?.setIcon(tabIcons[2]);
        tab.getTabAt(3)?.setIcon(tabIcons[3]);
    }
}
