package edu.ddukk.ddukkattendance

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.ddukk.ddukkattendance.adapters.ViewPagerAdapterAdmin

class AdminHomeActivity : AppCompatActivity() {
    //viewPager
    val viewPagerAdmin: ViewPager2 by lazy {
        findViewById(R.id.viewPager_admin)
    }
    val viewPagerAdapterAdmin: ViewPagerAdapterAdmin
            by lazy {
                ViewPagerAdapterAdmin(this)
            }
    val tabLayout: TabLayout by lazy {
        findViewById(R.id.tab_admin)
    }

    //drawer
    val drawerLayout: DrawerLayout by lazy {
        findViewById(R.id.drawer_layout_admin)
    }

    val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar_admin)
    }

    val actionBarDrawerToggle: ActionBarDrawerToggle
            by lazy {
                ActionBarDrawerToggle(
                    this, drawerLayout, toolbar,
                    R.string.open,
                    R.string.close
                )
            }

    val nav_view: NavigationView by lazy { findViewById(R.id.nav_view_admin_home) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_preferences)
        toolbar.setTitle("Hello")

        viewPagerAdapterAdmin.addFragment(
            TimetableFragment(), "TimeTable"
        )
        viewPagerAdapterAdmin.addFragment(
            AttendanceFragment(), "Attendance"
        )


        viewPagerAdmin.adapter = viewPagerAdapterAdmin

        TabLayoutMediator(tabLayout, viewPagerAdmin)
        { tab, position ->
            tab.text =
                viewPagerAdapterAdmin.getPageTitle(position)
        }.attach()

        nav_view.setNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_breaks -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            BreaksActivity::class.java
                        )
                    )
                }
            }

            drawerLayout.closeDrawers()


            return@setNavigationItemSelectedListener true

        }

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        return if (actionBarDrawerToggle
//                .onOptionsItemSelected(item)
//        )
//            true
//        else {
//            when (item.itemId) {
//                R.id.menu_breaks -> {
//                    startActivity(
//                        Intent(
//                            applicationContext,
//                            UserHomeActivity::class.java
//                        )
//                    )
//                }
//            }
//            return super.onOptionsItemSelected(item)
//        }
//
//    }
}