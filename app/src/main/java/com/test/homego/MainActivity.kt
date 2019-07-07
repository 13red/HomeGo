package com.test.homego

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.test.homego.ui.AdsListFragment
import com.test.homego.data.DataConnection
import com.test.homego.ui.AboutFragment
import com.test.homego.ui.BookmarksFragment
import com.test.homego.ui.model.ItemsViewModel

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    AdsListFragment.OnAdListFragmentListener{

    override fun onListFragmentInteraction() {
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.homeGo -> {
                // Clear saved data
                ViewModelProviders.of(this).get(ItemsViewModel::class.java).items = null
                setTitle(R.string.app_name)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentPlaceholder, AdsListFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
            R.id.bookmarks -> {
                setTitle(R.string.bookmarks)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentPlaceholder, BookmarksFragment.newInstance("", ""))
                    .commit()
            }
            R.id.about -> {
                setTitle(R.string.about)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentPlaceholder, AboutFragment.newInstance())
                    .commit()
            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init Menu and Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        // Start with Loading fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPlaceholder, AdsListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}