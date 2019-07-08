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
import com.test.homego.common.InfoFragmentDialog
import com.test.homego.data.BookmarksDbSingleton
import com.test.homego.data.model.Bookmark
import com.test.homego.data.model.Item
import com.test.homego.ui.*
import com.test.homego.ui.model.BookmarksViewModel
import com.test.homego.ui.model.ItemsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    AdDetailsFragment.OnAdDetailsFragmentListener,
    AdsListFragment.OnAdsListFragmentListener,
    BookmarksFragment.OnBookmarksFragmentListener {

    override fun onItemSelected(item: Item) {
        ViewModelProviders.of(this).get(ItemsViewModel::class.java).select(item)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPlaceholder, AdDetailsFragment.newInstance())
            .addToBackStack(getString(R.string.details))
            .commit()
    }

    override fun onPictureClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPlaceholder, PicturesFragment.newInstance())
            .addToBackStack(getString(R.string.pictures))
            .commit()
    }

    override fun onBookmarkSelected(bookmark: Bookmark) {
        val items = ViewModelProviders.of(this).get(ItemsViewModel::class.java).items

        items?.run {
            for (item in items) {
                if(item.advertisementId == bookmark.advertisementId) {
                    onItemSelected(item)
                    return
                }
            }

            // Bookmark is no longer available, remove it
            InfoFragmentDialog(R.string.removed_bookmark)
                .show(supportFragmentManager, getString(R.string.warning))
            GlobalScope.launch {
                BookmarksDbSingleton.getInstance(applicationContext).bookmarksDao().delete(bookmark)
            }
            showBookmarks()
        }

    }

    private fun showHome() {
        // Clear saved ads
        ViewModelProviders.of(this).get(ItemsViewModel::class.java).items = null
        val intent = intent
        finish()
        startActivity(intent)
    }

    private fun showBookmarks() {
        // Clear saved bookmarks
        ViewModelProviders.of(this).get(BookmarksViewModel::class.java).bookmarks = null
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPlaceholder, BookmarksFragment.newInstance())
            .addToBackStack(getString(R.string.bookmarks))
            .commit()
    }

    private fun showAbout() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPlaceholder, AboutFragment.newInstance())
            .addToBackStack(getString(R.string.about))
            .commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.homeGo -> showHome()
            R.id.bookmarks -> showBookmarks()
            R.id.about -> showAbout()
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
            .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            // Clear saved bookmarks
            ViewModelProviders.of(this).get(BookmarksViewModel::class.java).bookmarks = null
            super.onBackPressed()
        }
    }
}